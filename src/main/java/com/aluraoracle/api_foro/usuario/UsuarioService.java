package com.aluraoracle.api_foro.usuario;

import com.aluraoracle.api_foro.perfil.Perfil;
import com.aluraoracle.api_foro.perfil.PerfilNotFoundException;
import com.aluraoracle.api_foro.perfil.PerfilRepository;
import com.aluraoracle.api_foro.topico.TopicoRepository;
import com.aluraoracle.api_foro.usuario.exceptions.UsuarioNotFoundException;
import com.aluraoracle.api_foro.usuario.exceptions.UsuarioValidationException;
import com.aluraoracle.api_foro.usuario.record.DatosEditarUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosRegistroUsuario;
import com.aluraoracle.api_foro.usuario.record.DatosUsuarioShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private TopicoRepository topicoRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public Usuario crear(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByNombre(datos.nombre())) {
            throw new UsuarioValidationException("nombre", "Ya existe un usuario con ese nombre");
        }

        var usuario = new Usuario(datos.nombre(), datos.correoelectronico(), datos.contrasena(), datos.perfil());
        usuarioRepository.save(usuario);

        return usuario;
    }

    public List<DatosUsuarioShow> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(DatosUsuarioShow::new)
                .collect(Collectors.toList());
    }

    public Usuario editar(Long id, DatosEditarUsuario datos) {
        // Implementation for editing a user with DatosEditarUsuario
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario not found with id: " + id));
        usuario.setNombre(datos.nombre());
        usuario.setCorreoelectronico(datos.correoelectronico());
        usuario.setContrasena(datos.contrasena());
        // Assuming Perfil is an entity, and you have a method to find it by name
        Perfil perfil = perfilRepository.findByNombre(datos.perfil())
                .orElseThrow(() -> new PerfilNotFoundException("Perfil not found: " + datos.perfil()));
        usuario.setPerfil(perfil);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        // Delete related Topico records first
        topicoRepository.deleteByUsuarioId(id);
        // Then delete the Usuario
        usuarioRepository.deleteById(id);
    }
}