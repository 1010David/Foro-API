package com.aluraoracle.api_foro.controller;


import com.aluraoracle.api_foro.topico.DatosRegistroTopico;
import com.aluraoracle.api_foro.topico.DatosTopicoShow;
import com.aluraoracle.api_foro.topico.TopicoService;
import com.aluraoracle.api_foro.topico.TopicoValidationException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


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
            // Llamar al servicio para crear un nuevo tópico
            var topico = topicoService.crear(datosCrear);

            // Generar la URL del nuevo recurso
            URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

            // Retornar la respuesta con los datos del tópico creado
            return ResponseEntity.created(url).body(new DatosTopicoShow(topico));

        } catch (TopicoValidationException e) {
            // Manejar excepciones personalizadas y retornar un código HTTP 400
            return ResponseEntity.badRequest()
                    .body("Error en el campo: " + e.getCampo() + ". " + e.getMensaje());
        }
    }
}

