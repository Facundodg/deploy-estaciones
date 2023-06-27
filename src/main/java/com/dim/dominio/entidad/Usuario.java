package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "cuenta")
    private String cuenta;

    @Column(name = "contraseña")
    private String clave;

    @Column(name = "num_afiliado", updatable = false, nullable = false)
    private Long numAfiliado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Estacion> estaciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;

        return getIdUsuario().equals(usuario.getIdUsuario());
    }

    @Override
    public int hashCode() {
        return getIdUsuario().hashCode();
    }
}
