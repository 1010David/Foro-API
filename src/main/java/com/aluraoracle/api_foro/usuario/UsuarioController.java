package com.aluraoracle.api_foro.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Usuario usuario) {
        if (usuarioService.validateUser(usuario.getEmail(), usuario.getPassword())) {
            String token = jwtUtil.generateToken(usuario.getEmail());
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        }
        return ResponseEntity.status(403).body("Credenciales incorrectas");
    }
}
