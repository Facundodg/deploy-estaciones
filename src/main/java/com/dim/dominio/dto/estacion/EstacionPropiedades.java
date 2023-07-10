package com.dim.dominio.dto.estacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EstacionPropiedades {

    private String nombre;

    private String apellido;

    private Long puerto;

    private String hostname;

    @Column(name = "num_cusi")
    private Long numCusi;

    private String so;

    @Column(name = "nombre_depto")
    private String nombreDepartamento;
}
