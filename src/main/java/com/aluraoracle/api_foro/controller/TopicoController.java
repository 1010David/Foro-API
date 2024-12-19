package com.aluraoracle.api_foro.controller;


import com.aluraoracle.api_foro.domain.Topico;
import com.aluraoracle.api_foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Endpoint para listar todos los tópicos
    @GetMapping
    public List<Topico> listarTopicos() {
        return topicoService.obtenerTodos();
    }

    // Endpoint para obtener un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerPorId(id);
        return ResponseEntity.ok(topico);
    }

    // Endpoint para crear un nuevo tópico
    @PostMapping
    public ResponseEntity<Topico> crearTopico(@Valid @RequestBody Topico topico) {
        Topico nuevoTopico = topicoService.crearTopico(topico);
        return ResponseEntity.ok(nuevoTopico);
    }

    // Endpoint para actualizar un tópico existente
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody Topico topicoActualizado) {
        Topico topico = topicoService.actualizarTopico(id, topicoActualizado);
        return ResponseEntity.ok(topico);
    }

    // Endpoint para eliminar un tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
