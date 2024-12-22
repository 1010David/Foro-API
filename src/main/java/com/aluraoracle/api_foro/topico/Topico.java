package com.aluraoracle.api_foro.topico;

import com.aluraoracle.api_foro.curso.Categoria;
import com.aluraoracle.api_foro.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico",
        uniqueConstraints = {@UniqueConstraint(name = "ui_topico_titulo_mensaje", columnNames = {"titulo", "mensaje"})})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechacreacion;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Aquí se cambia la relación con Curso a la enumeración Categoria
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria curso; // Ahora el curso es un enum

    public Topico(String titulo, String mensaje, Usuario usuario, Categoria curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechacreacion = LocalDateTime.now();
        this.estado = Estado.ABIERTO;
        this.usuario = usuario;
        this.curso = curso;
    }
}
