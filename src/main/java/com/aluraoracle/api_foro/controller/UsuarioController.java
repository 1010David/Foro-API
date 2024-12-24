package com.aluraoracle.api_foro.controller;

import com.aluraoracle.api_foro.usuario.Usuario;
import com.aluraoracle.api_foro.usuario.UsuarioService;
import com.aluraoracle.api_foro.usuario.exceptions.UsuarioNotFoundException;
import com.aluraoracle.api_foro.usuario.exceptions.UsuarioValidationException;
import com.aluraoracle.api_foro.usuario.record.DatosEditarUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosRegistroUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosUsuarioShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> crear(@RequestBody @Valid DatosRegistroUsuario datosCrear, UriComponentsBuilder uriComponentsBuilder) {
        try {
            var usuario = usuarioService.crear(datosCrear);
            URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosUsuarioShow(usuario));
        } catch (UsuarioValidationException e) {
            return ResponseEntity.badRequest().body("Error en el campo: " + e.getCampo() + ". " + e.getMensaje());
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosUsuarioShow>> obtenerTodos() {
        List<DatosUsuarioShow> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody @Valid DatosEditarUsuario datos) {
        Usuario usuarioActualizado = usuarioService.editar(id, datos);
        return ResponseEntity.ok(usuarioActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}