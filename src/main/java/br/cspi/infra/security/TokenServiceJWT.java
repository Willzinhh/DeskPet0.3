package br.cspi.infra.security;

import br.cspi.model.usuario.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {



    public String gerarToken(User user) {

        try{
            Algorithm algorithm = Algorithm.HMAC256("POO2");
            return JWT.create()
                    .withIssuer("API DeskPet")
                    .withSubject(user.getEmail())
                    .withClaim("ROLE",user.getPermissao())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256("POO2");
            return JWT.require(algorithm)
                    .withIssuer("API DeskPet")
                    .build().verify(token)
                    .getSubject();
        }catch (JWTCreationException e){
            throw new RuntimeException("Token invalido ou expirado");
        }
    }


}
