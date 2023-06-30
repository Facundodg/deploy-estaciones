package com.dim.dominio.dto.cusi;

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
public class CusiAlta {

    private Long numCusi;

    private String micro;

    private String mother;

    private String ram;

    private String disco;

    private String so;

    private boolean mouse;

    private boolean teclado;

    private boolean dvd;

    private String ip;
}
