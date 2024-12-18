package com.aluraoracle.api_foro.topico;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    // Listar todos los tópicos (GET /topicos)
    @GetMapping
    public ResponseEntity<List<Topico>> getAllTopicos() {
        List<Topico> topicos = topicoService.getAllTopicos();
        return ResponseEntity.ok(topicos);
    }

    // Crear un nuevo tópico (POST /topicos)
    @PostMapping
    public ResponseEntity<Topico> createTopico(@RequestBody Topico topico) {
        Topico nuevoTopico = topicoService.saveTopico(topico);
        return ResponseEntity.status(201).body(nuevoTopico);
    }

    // Eliminar un tópico por ID (DELETE /topicos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopico(@PathVariable Long id) {
        boolean eliminado = topicoService.deleteTopico(id);
        if (eliminado) {
            return ResponseEntity.ok().body("Tópico eliminado exitosamente");
        } else {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }
    }
}

