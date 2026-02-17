package fabiocarlino.u5l12.controllers;

import fabiocarlino.u5l12.payloads.LoginDTO;
import fabiocarlino.u5l12.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO body) {
        return this.authService.CheckCredentialAndGenerateToken(body);
    }
}
