package com.dim.controlador.interfaz;

import com.dim.entidad.Estacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Validated
@RequestMapping("/estacion")
@Tag(name = "Estación", description = "Estación API")
public interface EstacionApi {

    @Operation(summary = "Crea un estacion", description = "Retorna el estacion creado")
    @PostMapping(value = "",
            consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Estacion> guardar(
            @NotNull
            @Parameter(description = "Objeto de estacion que se desea crear", required = true)
            @RequestBody @Valid final Estacion entidad) throws Exception;

    @Operation(summary = "Modifica un estacion", description = "Devuelve el estacion modificado")
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Estacion> modificar(
            @Parameter(description = "ID del estacion", required = true)
            @PathVariable("id") @Min(1) final Long id,
            @NotNull
            @Parameter(description = "Objeto del estacion con los nuevos datos a modificar", required = true)
            @RequestBody @Valid final Estacion entidad) throws Exception;

    @Operation(summary = "Busca un estacion por ID", description = "Retorna un único estacion")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Estacion> buscarPorId(
            @Parameter(description = "ID del estacion", required = true)
            @PathVariable("id") @Min(1) final Long id) throws Exception;

    @Operation(summary = "Busca todos los estacion", description = "Retorna todos los estacions")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Collection<Estacion>> buscarTodos() throws Exception;
}
