package com.dim.controlador.interfaz;

import com.dim.dominio.dto.general.BuscarEstacion;
import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.dto.general.ConjuntoAlta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Validated
@RequestMapping("/main")
@Tag(name = "Main", description = "Main API")
public interface PrincipalApi {

    @Operation(summary = "Registra una estación completa", description = "Retorna la estación cargada")
    @PostMapping(value = "",
            consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EstacionPropiedades> cargarEstacionCompleta(
            @NotNull
            @Parameter(description = "Objeto de la estación que se desea registrar", required = true)
            @RequestBody @Valid final ConjuntoAlta conjuntoAlta) throws Exception;

    @PostMapping("/buscarPorPuertoCusiUsuario")
    @Operation(summary = "Búsqueda filtrada por tipo", description = "Retorna departamento, cusi o estación")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> filtrarBusqueda(
            @NotNull
            @Parameter(description = "Objeto de búsqueda para filtrar", required = true)
            @RequestBody @Valid final BuscarEstacion buscarEstacion) throws Exception;

    @Operation(summary = "Búsqueda general de estaciones con información genérica", description = "Retorna lista de información de estaciones")
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Collection<EstacionPropiedades>> buscarEstacionesPropiedades() throws Exception;


    @Operation(summary = "Modifica una estación completa", description = "Retorna la estación cargada")
    @PutMapping(value = "",
            consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EstacionPropiedades> modificarEstacionCompleta(
            @NotNull
            @Parameter(description = "Objeto de la estación que se desea modificar", required = true)
            @RequestBody @Valid final ConjuntoAlta conjuntoAlta) throws Exception;

}
