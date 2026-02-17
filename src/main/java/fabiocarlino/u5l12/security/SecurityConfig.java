package fabiocarlino.u5l12.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // È la classe di configurazione PER SPRING SECURITY
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        //Disabilita autenticazione via form
        httpSecurity.formLogin(formLogin -> formLogin.disable());
        //Disabilita la protezione da attacchi CSRF
        httpSecurity.csrf(csrf -> csrf.disable());
        //Disabilita la modalità di lavoro a sessione
        httpSecurity.sessionManagement(sessions -> sessions.sessionCreationPolicy((SessionCreationPolicy.STATELESS)));
        //Disabilita la verifica dell'autorizzazione da parte di Spring Security (NO ERROR 401)
        httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/**").permitAll());

        return httpSecurity.build();
    }
}
