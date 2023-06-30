package com.dim.dominio.dto.general;

import com.dim.dominio.dto.cusi.CusiAlta;
import com.dim.dominio.dto.estacion.EstacionAlta;
import com.dim.dominio.entidad.Monitor;
import com.dim.dominio.entidad.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConjuntoAlta {

    private long departamento;

    private EstacionAlta estacion;

    private CusiAlta cusi;

    private Monitor monitor;

    private Usuario usuario;
}
