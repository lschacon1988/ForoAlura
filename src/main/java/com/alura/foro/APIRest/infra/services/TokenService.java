package com.alura.foro.APIRest.infra.services;

import com.alura.foro.APIRest.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("api.security.secret")
    private String secreKey;

    public String generateToken(User user) {
        try {
            System.out.println(secreKey);
            Algorithm algorithm = Algorithm.HMAC256(secreKey);

            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("foroAlura")
                    .withClaim("id", user.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Token no puede ser nulo");
        }

        if (secreKey == null) {
            throw new IllegalArgumentException("secreKey no puede ser nulo");
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreKey);
            verifier = JWT.require(algorithm)
                    .withIssuer("foroAlura")
                    .build()
                    .verify(token);

            String subject = verifier.getSubject();
            if (subject == null) {
                throw new RuntimeException("Verifier inválido");
            }
            return subject;
        } catch (JWTVerificationException exception) {
            System.out.println("MENSAJE DE ERROR "+exception.getMessage());
            throw new RuntimeException("Error al verificar el token", exception);
        }
    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusMinutes(30L).toInstant(ZoneOffset.of("-05:00"));


    }
}
