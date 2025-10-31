package br.cspi.infra.security;

import br.cspi.infra.exceptions.ErroResponse;
import br.cspi.service.AutenticacaoService;
import br.cspi.infra.exceptions.TratadorDeErros.*;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AutenticacaoFilter extends OncePerRequestFilter {

    private TokenServiceJWT tokenService;
    private AutenticacaoService autenticacaoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filtro para autenticar e autorizar");
        System.out.println("filtro para autenticação e autorização");

        String token = this.recuperaToken(request);
        System.out.println("Token: " + token);

        if (token != null) {
            try {

                String subject = this.tokenService.getSubject(token);
                System.out.println("Login: " + subject);


                UserDetails userDetails = this.autenticacaoService.loadUserByUsername(subject);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                var erroResponse = new ErroResponse(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "Falha na Autenticação (Token Inválido ou Expirado)");
                new ObjectMapper().writeValue(response.getWriter(), erroResponse);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public String recuperaToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer ", "").trim();
        }
        return null;
    }
}
