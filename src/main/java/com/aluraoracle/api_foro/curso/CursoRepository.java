package com.aluraoracle.api_foro.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Métodos adicionales personalizados si son necesarios
}
