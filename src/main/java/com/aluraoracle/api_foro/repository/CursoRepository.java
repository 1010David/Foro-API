package com.aluraoracle.api_foro.repository;

import com.aluraoracle.api_foro.curso.Categoria;
import com.aluraoracle.api_foro.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCategoria(Categoria categoria);
}

