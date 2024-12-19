package com.aluraoracle.api_foro.repository;

import com.aluraoracle.api_foro.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Corregir el nombre del método para coincidir con 'correoElectronico'
    boolean existsByCorreoElectronico(String correoElectronico);
}
