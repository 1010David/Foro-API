package com.aluraoracle.api_foro.usuario;

import com.aluraoracle.api_foro.usuario.record.DatosActualizarUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosCrearUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosVerUsuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un nuevo usuario
    public DatosVerUsuario crearUsuario(DatosCrearUsuario datosCrear) {
        Usuario usuario = new Usuario(
                datosCrear.nombre(),
                datosCrear.correoElectronico(),
                datosCrear.contrasena()
        );
        usuarioRepository.save(usuario);
        return new DatosVerUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }

    // Actualizar un usuario existente
    public DatosVerUsuario actualizarUsuario(DatosActualizarUsuario datosActualizar) {
        Usuario usuario = usuarioRepository.findById(datosActualizar.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (datosActualizar.nombre() != null) {
            usuario.setNombre(datosActualizar.nombre());
        }
        if (datosActualizar.correoElectronico() != null) {
            usuario.setCorreoElectronico(datosActualizar.correoElectronico());
        }

        usuarioRepository.save(usuario);
        return new DatosVerUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }

    // Listar todos los usuarios
    public List<DatosVerUsuario> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new DatosVerUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico()))
                .collect(Collectors.toList());
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

