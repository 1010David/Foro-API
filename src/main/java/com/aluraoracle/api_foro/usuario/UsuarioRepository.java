package com.aluraoracle.api_foro.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNombre(String nombre);

    Optional<Usuario> findBynombre(String nombre);


}


