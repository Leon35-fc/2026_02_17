package fabiocarlino.u5l12.services;

import fabiocarlino.u5l12.entities.Dipendente;
import fabiocarlino.u5l12.exceptions.UnauthorizedException;
import fabiocarlino.u5l12.payloads.LoginDTO;
import fabiocarlino.u5l12.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final DipendentiService dipendentiService;
    private final JWTTools jwtTools;

    @Autowired
    public AuthService(DipendentiService dipendentiService, JWTTools jwtTools) {
        this.dipendentiService = dipendentiService;
        this.jwtTools = jwtTools;
    }

    public String CheckCredentialAndGenerateToken(LoginDTO body) {
        // 1) Controllo le credenziali.
        // - 1) Controllo che l'e-mail sia associata ad un utente
        Dipendente found = this.dipendentiService.findByEmail(body.email());

        // - 2) Verificato il punto 1), controlla se la password esiste nel DB e che sia uguale a quella del body
        if (found.getPassword().equals(body.password())) {
            //2) Credenziali a posto
            //- 1) Genero token
            String accessToken = jwtTools.generateToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }

    }
}
