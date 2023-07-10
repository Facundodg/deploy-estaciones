package com.dim.dominio.dto.usuario;

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
public class UsuarioAlta {

    @Min(value = 1, message = "Numero de Afiliado no debe ser negativo")
    @NotNull(message = "Número de Afiliado no debe ser nulo")
    private Long numAfiliado;

    @NotNull(message = "Cuenta no debe ser nulo")
    @Size(min = 3, message = "Cuenta debe tener por lo menos 3 caracteres")
    private String cuenta;

    @NotNull(message = "Clave no debe ser nulo")
    @Size(min = 3, message = "Clave debe tener por lo menos 3 caracteres")
    private String clave;

}
