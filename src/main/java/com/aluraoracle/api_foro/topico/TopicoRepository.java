package com.aluraoracle.api_foro.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    void deleteByUsuarioId(Long usuarioId);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}