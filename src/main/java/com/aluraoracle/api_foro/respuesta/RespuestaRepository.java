package com.aluraoracle.api_foro.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    // Métodos adicionales personalizados si son necesarios
}
