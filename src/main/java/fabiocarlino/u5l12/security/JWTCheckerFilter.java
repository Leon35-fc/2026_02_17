package fabiocarlino.u5l12.security;

import fabiocarlino.u5l12.exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;

    @Autowired
    public JWTCheckerFilter(JWTTools jwtTools) {
        this.jwtTools = jwtTools;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //VERIFICO CHE LA RICHIESTA CONTIENE L'HEADER E CHE SIA IN FORMATO BEARER
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization header in formato corretto");

        //  ESTRAGGO IL TOKEN DALL'HEADER
        String accessToken = authHeader.replace("Bearer ", "");

        // VERIFICO SE IL TOKEN è VALIDO
        jwtTools.verifyToken(accessToken);

        filterChain.doFilter(request, response);
        // se ci sono problemi lancio eccezione
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
