package com.aluraoracle.api_foro.repository;

import com.aluraoracle.api_foro.domain.Topico;
import com.aluraoracle.api_foro.enums.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);

    // Corrección: Cambiar 'Fechacreacion' a 'FechaCreacion'
    Page<Topico> findByOrderByFechaCreacionDesc(Pageable pageable);

    Page<Topico> findTop10ByEstadoOrderByFechaCreacionDesc(Estado estado, Pageable pageable);
}
