package com.dim.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estacionusuario")
public class EstacionUsuario {

    @Id
    @Column(name = "id_estacionusuario", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStacion;

    @Column(name = "id_estacion")
    private Long idEstacion;

    @Column(name = "id_usuario")
    private Long idUsuario;
}