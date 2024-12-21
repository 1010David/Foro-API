package com.aluraoracle.api_foro.repository;

import com.aluraoracle.api_foro.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}

