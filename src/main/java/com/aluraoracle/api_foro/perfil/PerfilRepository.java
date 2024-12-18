package com.aluraoracle.api_foro.perfil;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    // Métodos adicionales personalizados si son necesarios
}

