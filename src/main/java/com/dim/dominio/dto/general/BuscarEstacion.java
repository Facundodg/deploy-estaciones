package com.dim.dominio.dto.general;

import com.dim.dominio.enumeracion.TipoBusqueda;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuscarEstacion {

    @NotNull(message = "BuscarPor no debe ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoBusqueda buscarPor;

    @NotNull(message = "Buscar no debe ser nulo")
    @Size(min = 1, max = 10, message = "Buscar debe tener entre 1 y 10 caracteres")
    private String buscar;
}