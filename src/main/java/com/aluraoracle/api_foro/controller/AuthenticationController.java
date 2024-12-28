package com.aluraoracle.api_foro.controller;

import com.aluraoracle.api_foro.infra.security.DatosJWToken;
import com.aluraoracle.api_foro.infra.security.TokenService;
import com.aluraoracle.api_foro.usuario.Usuario;
import com.aluraoracle.api_foro.usuario.record.DatosAuthUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody DatosAuthUsuario datosAuthUsuario) {
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuthUsuario.nombre(), datosAuthUsuario.contrasena());
            Authentication usuarioAuth = authenticationManager.authenticate(authToken);
            String JWToken = tokenService.gerarToken((Usuario) usuarioAuth.getPrincipal());
            return ResponseEntity.ok(new DatosJWToken(JWToken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
    /*@PostMapping
    public Object authenticateUser(@RequestBody @Valid DatosAuthUsuario datosAuthUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuthUsuario.nombre(),
                datosAuthUsuario.contrasena());
        var usuarioAuth = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.gerarToken((Usuario) usuarioAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(JWToken));
    }*/



    /* @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody DatosAuthUsuario datosAuthUsuario) {
        try {
            Authentication token = new UsernamePasswordAuthenticationToken(datosAuthUsuario.nombre(), datosAuthUsuario.contrasena());
            authenticationManager.authenticate(token);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }*/
}


