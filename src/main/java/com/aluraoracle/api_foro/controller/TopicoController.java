package com.aluraoracle.api_foro.controller;


import com.aluraoracle.api_foro.topico.DatosRegistroTopico;
import com.aluraoracle.api_foro.topico.DatosTopicoShow;
import com.aluraoracle.api_foro.topico.TopicoService;
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
    public ResponseEntity<DatosTopicoShow> crear(
            @RequestBody @Valid DatosRegistroTopico datosCrear,
            UriComponentsBuilder uriComponentsBuilder) {
        var datosVer = new DatosTopicoShow(topicoService.crear(datosCrear));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosVer.id()).toUri();
        return ResponseEntity.created(url).body(datosVer);
    }
}
