package com.aluraoracle.api_foro.infra.security;

import com.aluraoracle.api_foro.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String APIsecret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("APIsecret");
            return JWT.create()
                    .withIssuer("api_foro")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())

                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }

    }
}
