package com.aluraoracle.api_foro.perfil;

import com.aluraoracle.api_foro.usuario.Usuario;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfil", uniqueConstraints = { @UniqueConstraint(name = "ui_perfil_nombre", columnNames = {"nombre"}) })
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonDeserialize(using = PerfilDeserializer.class)
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToMany(mappedBy = "perfiles", fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();
}