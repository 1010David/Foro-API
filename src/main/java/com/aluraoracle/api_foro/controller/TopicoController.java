package com.aluraoracle.api_foro.controller;

import com.aluraoracle.api_foro.topico.record.DatosTopicoShow;
import com.aluraoracle.api_foro.topico.exceptions.TopicoNotFoundException;
import com.aluraoracle.api_foro.topico.exceptions.TopicoValidationException;
import com.aluraoracle.api_foro.topico.*;
import com.aluraoracle.api_foro.topico.record.DatosRegistroTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> crear(
            @RequestBody @Valid DatosRegistroTopico datosCrear,
            UriComponentsBuilder uriComponentsBuilder) {
        try {
            var topico = topicoService.crear(datosCrear);
            URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosTopicoShow(topico));
        } catch (TopicoValidationException e) {
            return ResponseEntity.badRequest()
                    .body("Error en el campo: " + e.getCampo() + ". " + e.getMensaje());
        }
    }

    @GetMapping
    public ResponseEntity<List<DatosTopicoShow>> obtenerTodos() {
        List<DatosTopicoShow> topicos = topicoService.obtenerTodos();
        return ResponseEntity.ok(topicos);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosTopicoShow> editar(
            @PathVariable Long id,
            @RequestBody @Valid DatosRegistroTopico datosEditar) {
        var topico = topicoService.editar(id, datosEditar);
        return ResponseEntity.ok(new DatosTopicoShow(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            topicoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (TopicoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}