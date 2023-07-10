package com.dim.dominio.dto.general;

import com.dim.dominio.dto.cusi.CusiAlta;
import com.dim.dominio.dto.estacion.EstacionAlta;
import com.dim.dominio.dto.monitor.MonitorAlta;
import com.dim.dominio.dto.usuario.UsuarioAlta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConjuntoAlta {

    @Min(value = 1, message = "Departamento no debe ser negativo")
    @NotNull(message = "Departamento no debe ser nulo")
    private long departamento;

    @Valid
    private EstacionAlta estacion;

    @Valid
    private CusiAlta cusi;

    @Valid
    private MonitorAlta monitor;

    @Valid
    private Collection<UsuarioAlta> usuario;
}
