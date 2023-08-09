package com.dim.dominio.dto.monitor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
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
public class MonitorAlta {

    /*
    @Min(value = 1, message = "Numero de Serie no debe ser negativo")
    @NotNull(message = "Número de Serie no debe ser nulo")


     */
    private Long numeroSerie;


    /*
    @NotNull(message = "Marca no debe ser nulo")
    @Size(min = 3, message = "Marca debe tener por lo menos 3 caracteres")


     */
    private String marca;

    /*


    @NotNull(message = "Modelo no debe ser nulo")
    @Size(min = 3, message = "Modelo debe tener por lo menos 3 caracteres")

     */
    private String modelo;


}
