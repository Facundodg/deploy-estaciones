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

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contraseña")
    private String clave;

    @Column(name = "num_afiliado", updatable = false, nullable = false)
    private Long numAfiliado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private Set<Estacion> estaciones;

}
