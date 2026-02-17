package fabiocarlino.u5l12.services;

import fabiocarlino.u5l12.entities.Viaggio;
import fabiocarlino.u5l12.payloads.ViaggiDTO;
import fabiocarlino.u5l12.repositories.DipendentiRepo;
import fabiocarlino.u5l12.repositories.ViaggiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggiService {
    private final ViaggiRepo viaggiRepo;
    private final DipendentiRepo dipendentiRepo;

    @Autowired

    public ViaggiService(ViaggiRepo viaggiRepo, DipendentiRepo dipendentiRepo) {
        this.viaggiRepo = viaggiRepo;
        this.dipendentiRepo = dipendentiRepo;
    }

    public Viaggio save(ViaggiDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.destinazione());
        viaggio.setData(viaggioDTO.data());
        viaggio.setStatoViaggio(viaggioDTO.statoViaggio());

//        if (viaggiRepo.findByData(viaggio.getData()) && ){
//
//        }

        Viaggio savedViaggio = viaggiRepo.save(viaggio);
        System.out.println("Il viaggio con id: " + savedViaggio.getId() + " è stato salvato correttamente!");
        return savedViaggio;
    }
}
