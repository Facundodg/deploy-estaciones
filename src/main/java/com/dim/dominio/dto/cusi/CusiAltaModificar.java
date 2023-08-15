package com.dim.dominio.dto.cusi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CusiAltaModificar {

    private Long id;

    private Long numCusi;

    private String micro;


    private String mother;


    private String ram;


    private String disco;


    private String so;


    private boolean mouse;


    private boolean teclado;


    private boolean dvd;


    private InetAddress ip;


    private String mac;


    private String hostName;

}
