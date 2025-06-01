package br.com.valeway.router.transportation.config;

import br.com.valeway.router.transportation.domain.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(AppUser user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("login-auth-api") // Define o emissor do token
                    .withSubject(user.getEmail()) // Define o assunto do token (neste caso, o nome de usuário)
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm); // Assina o token usando o algoritmo especificado
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar token.", exception);
        }
    }

    public String validaToken(String token) {
           try {
               Algorithm algorithm = Algorithm.HMAC256(secret);
               return JWT.require(algorithm)
                       .withIssuer("login-auth-api")
                        .build()
                       .verify(token)
                       .getSubject();
           } catch (JWTVerificationException exception) {
               return null;
           }
    }


    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.ofHours(-3));
    }




}
