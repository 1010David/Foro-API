package com.aluraoracle.api_foro.usuario;

import com.aluraoracle.api_foro.perfil.Perfil;
import com.aluraoracle.api_foro.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(name = "ui_usuario_nombre", columnNames = {"nombre"}),
                @UniqueConstraint(name = "ui_usuario_correoelectronico", columnNames = {"correoelectronico"})
        }
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

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

    public Usuario(String nombre, String correoelectronico, String contrasena, @NotNull(message = "El perfil no puede estar vacío") Perfil perfil) {
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

    public void setPerfil(Perfil perfil) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfiles.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}