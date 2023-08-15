package com.dim.dominio.entidad;

import lombok.Data;

@Data
public class DataUsuario {

    private long id_usuario;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellido;
    private Long idDepartamento;

}
