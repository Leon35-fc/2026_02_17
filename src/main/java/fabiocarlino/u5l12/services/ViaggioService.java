package fabiocarlino.u5l12.services;

import fabiocarlino.u5l12.entities.StatoViaggio;
import fabiocarlino.u5l12.entities.Viaggio;
import fabiocarlino.u5l12.exceptions.NotFoundException;
import fabiocarlino.u5l12.payloads.ViaggioDTO;
import fabiocarlino.u5l12.repositories.ViaggiRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ViaggioService {
    private final ViaggiRepo viaggiRepo;

    @Autowired
    public ViaggioService(ViaggiRepo viaggiRepo) {
        this.viaggiRepo = viaggiRepo;
    }

    public Page<Viaggio> findAll(int page, int size, String orderBy, String sortCriteria) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equalsIgnoreCase("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.viaggiRepo.findAll(pageable);
    }

    public Viaggio saveViaggio(ViaggioDTO payload) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(payload.destinazione());
        viaggio.setData(payload.dataViaggio());
        viaggio.setStatoViaggio(payload.statoViaggio());

        Viaggio savedViaggio = viaggiRepo.save(viaggio);
        log.info("il viaggio con id: " + savedViaggio.getId() + " è stato salvato correttamente!");
        return savedViaggio;
    }

    public Viaggio findById(UUID id) {
        return viaggiRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio findByIdAndUpdate(UUID viaggioId, ViaggioDTO payload) {
        Viaggio found = this.findById(viaggioId);
        found.setDestinazione(payload.destinazione());
        found.setStatoViaggio(payload.statoViaggio());
        found.setData(payload.dataViaggio());
        return viaggiRepo.save(found);
    }

    public void findByIdAndDelete(UUID viaggioId) {
        Viaggio found = this.findById(viaggioId);
        this.viaggiRepo.delete(found);
    }

    public Viaggio updateStatoViaggio(UUID viaggioId, StatoViaggio newStatoViaggio) {
        Viaggio found = this.findById(viaggioId);
        found.setStatoViaggio(newStatoViaggio);
        return viaggiRepo.save(found);
    }
}
