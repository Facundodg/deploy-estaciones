package com.dim.dominio.dto.cusi;

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
public class CusiAlta {

    @Min(value = 1, message = "Número de Cusi no debe ser negativo")
    @NotNull(message = "Número de Cusi no debe ser nulo")
    private Long numCusi;

    @NotNull(message = "Micro no debe ser nulo")
    @Size(min = 3, message = "Micro debe tener por lo menos 3 caracteres")
    private String micro;

    @NotNull(message = "Mother no debe ser nulo")
    @Size(min = 3, message = "Mother debe tener por lo menos 3 caracteres")
    private String mother;

    @NotNull(message = "RAM no debe ser nulo")
    @Size(min = 3, message = "RAM debe tener por lo menos 3 caracteres")
    private String ram;

    @NotNull(message = "Disco no debe ser nulo")
    @Size(min = 3, message = "Disco debe tener por lo menos 3 caracteres")
    private String disco;

    @NotNull(message = "SO no debe ser nulo")
    @Size(min = 3, message = "SO debe tener por lo menos 3 caracteres")
    private String so;

    @NotNull(message = "Hostname no debe ser nulo")
    @Size(min = 3, message = "Hostname debe tener por lo menos 3 caracteres")
    private String hostName;

    @NotNull(message = "IP no debe ser nulo")
    @Size(min = 3, message = "IP debe tener por lo menos 3 caracteres")
    private String ip;

    @NotNull(message = "MAC no debe ser nulo")
    @Size(min = 3, message = "MAC debe tener por lo menos 3 caracteres")
    private String mac;

    @NotNull(message = "Mouse no debe ser nulo")
    private boolean mouse;

    @NotNull(message = "Teclado no debe ser nulo")
    private boolean teclado;

    @NotNull(message = "DVD no debe ser nulo")
    private boolean dvd;
}
