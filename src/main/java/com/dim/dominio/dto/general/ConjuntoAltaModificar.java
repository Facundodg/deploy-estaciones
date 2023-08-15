package com.dim.dominio.dto.general;

import com.dim.dominio.dto.cusi.CusiAlta;
import com.dim.dominio.dto.cusi.CusiAltaModificar;
import com.dim.dominio.dto.estacion.EstacionAlta;
import com.dim.dominio.dto.estacion.EstacionAltaModificar;
import com.dim.dominio.dto.monitor.MonitorAlta;
import com.dim.dominio.dto.monitor.MonitorAltaModificar;
import com.dim.dominio.dto.usuario.UsuarioAlta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConjuntoAltaModificar {

    /*
@Min(value = 1, message = "Departamento no debe ser negativo")
@NotNull(message = "Departamento no debe ser nulo")

 */

    private EstacionAltaModificar estacion;

    private CusiAltaModificar cusi;

    private MonitorAltaModificar monitor;

}
