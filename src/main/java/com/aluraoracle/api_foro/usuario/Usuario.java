package com.aluraoracle.api_foro.usuario;

import com.aluraoracle.api_foro.perfil.Perfil;
import com.aluraoracle.api_foro.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(name = "ui_usuario_nombre", columnNames = {"nombre"}),
                @UniqueConstraint(name = "ui_usuario_correoelectronico", columnNames = {"correoelectronico"})
        }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String correoelectronico;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "perfil_usuario",
            joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_perfil_usuario_usuario_id")),
            inverseJoinColumns = @JoinColumn(name = "perfil_id", foreignKey = @ForeignKey(name = "fk_perfil_usuario_perfil_id"))
    )
    @OrderBy("nombre ASC")
    private List<Perfil> perfiles = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Topico> topicos = new ArrayList<>();

    public Usuario(String nombre, String correoelectronico, String contrasena, Perfil perfil) {
        this.nombre = nombre;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.perfiles.add(perfil);
    }

    public void actualizar(String nombre, String correoelectronico, String contrasena) {
        this.nombre = nombre;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
    }
}
