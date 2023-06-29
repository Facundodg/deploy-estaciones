package com.dim.controlador.interfaz;

import com.dim.dominio.dto.BusquedaDto;
import com.dim.dominio.dto.EstacionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

@Validated
@RequestMapping("/principal")
@Tag(name = "Principal", description = "Principal API")
public interface PrincipalApi {

    @GetMapping("/")
    @Operation(summary = "Búsqueda filtrada por tipo", description = "Retorna departamento, cusi o estación")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> filtrarBusqueda(
            @NotNull
            @Parameter(description = "Objeto de búsqueda para filtrar", required = true)
            @RequestBody @Valid final BusquedaDto busquedaDto) throws Exception;

    @Operation(summary = "Búsqueda general de estaciones con información genérica", description = "Retorna lista de información de estaciones")
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Collection<EstacionDto>> busquedaPrincipal() throws Exception;
}
