package com.dim.entidad;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String cuenta;
    private String contraseña;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EstacionUsuario> estacionUsuarios;



}
