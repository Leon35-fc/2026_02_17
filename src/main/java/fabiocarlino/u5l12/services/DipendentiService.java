package fabiocarlino.u5l12.services;

import com.cloudinary.Cloudinary;
import fabiocarlino.u5l12.entities.Dipendente;
import fabiocarlino.u5l12.exceptions.BadRequestException;
import fabiocarlino.u5l12.exceptions.NotFoundException;
import fabiocarlino.u5l12.payloads.DipendenteDTO;
import fabiocarlino.u5l12.repositories.DipendentiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DipendentiService {
    private final DipendentiRepo dipendentiRepo;
    List dipendentiList = new ArrayList<>();

//    private final Cloudinary cloudinaryUploader;

    @Autowired
    public DipendentiService(DipendentiRepo dipendentiRepo, Cloudinary cloudinaryUploader) {
        this.dipendentiRepo = dipendentiRepo;
//        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Dipendente save(DipendenteDTO dipendenteDTO) {

        //CONTROLLO SULL'E-MAIL
        this.dipendentiRepo.findByEmail(dipendenteDTO.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'e-mail " + dipendenteDTO.email() + "è già in uso");
        });

        //CREO UN NUOVO DIPENDENTE E GLI AGGIUNGO UN'IMMAGINE DI DEFAULT
        Dipendente newDipendente = new Dipendente(
                dipendenteDTO.nome(),
                dipendenteDTO.cognome(),
                dipendenteDTO.username(),
                dipendenteDTO.email(),
                dipendenteDTO.password());
        newDipendente.setAvatar("https://ui-avatars.com/api?name=" + dipendenteDTO.nome() + "+" + dipendenteDTO.cognome());

        //SALVO E RITORNO IL DIPENDENTE
        Dipendente dipendenteSalvato = this.dipendentiRepo.save(newDipendente);
        System.out.println("Il dipendente con id \"" + dipendenteSalvato.getId() + "\" è stato salvato.");
        return dipendenteSalvato;
    }

    public List<Dipendente> findAll() {

        return this.dipendentiList;
    }

    public Dipendente findByEmail(String email) {
        return this.dipendentiRepo.findByEmail(email).
                orElseThrow(() -> new NotFoundException("L'utente con email \"" + email + "\" non è stato trovato."));
    }

    public Dipendente findById(UUID id) {
        return dipendentiRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

}
