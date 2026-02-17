package fabiocarlino.u5l12.services;

import fabiocarlino.u5l12.entities.Dipendente;
import fabiocarlino.u5l12.entities.Prenotazione;
import fabiocarlino.u5l12.entities.Viaggio;
import fabiocarlino.u5l12.exceptions.BadRequestException;
import fabiocarlino.u5l12.exceptions.NotFoundException;
import fabiocarlino.u5l12.payloads.PrenotazioneDTO;
import fabiocarlino.u5l12.repositories.PrenotazioniRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioneService {

    private final PrenotazioniRepo prenotazioneRepo;

    private final DipendentiService dipendentiService;

    private final ViaggioService viaggioService;

    @Autowired
    public PrenotazioneService(PrenotazioniRepo prenotazioneRepo, DipendentiService dipendentiService, ViaggioService viaggioService) {
        this.prenotazioneRepo = prenotazioneRepo;
        this.dipendentiService = dipendentiService;
        this.viaggioService = viaggioService;
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO payload) {
        //recupero il dipendente e il viaggio
        Dipendente dipendente = dipendentiService.findById(payload.dipendenteId());
        Viaggio viaggio = viaggioService.findById(payload.viaggioId());
        //controllo se il dipendente ha già un viaggio per quella data
        if (prenotazioneRepo.existsByDipendenteIdAndViaggio_DataViaggio(
                dipendente.getId(), viaggio.getData())) {
            throw new BadRequestException("il dipendente " + dipendente.getUsername() +
                    " ha già un viaggio prenotato in data: " + viaggio.getData());
        }
        // nel caso tutto fila liscio creo la prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendenteId(dipendente);
        prenotazione.setViaggioId(viaggio);
        prenotazione.setNote(payload.note());
        prenotazione.setDataRichiesta(LocalDate.now());

        return prenotazioneRepo.save(prenotazione);
    }

    public Page<Prenotazione> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equalsIgnoreCase("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.prenotazioneRepo.findAll(pageable);
    }

    public Prenotazione findById(UUID id) {
        return prenotazioneRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID prenotazioneId) {
        Prenotazione found = this.findById(prenotazioneId);
        this.prenotazioneRepo.delete(found);
    }
}
