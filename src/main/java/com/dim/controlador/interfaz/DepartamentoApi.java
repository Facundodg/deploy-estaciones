package com.dim.controlador.interfaz;

import com.dim.entidad.Departamento;
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
@RequestMapping("/departamento")
@Tag(name = "Departamento", description = "Departamento API")
public interface DepartamentoApi {

    @Operation(summary = "Crea un departamento", description = "Retorna el departamento creado")
    @PostMapping(value = "",
            consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"},
            produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Departamento> guardar(
            @NotNull
            @Parameter(description = "Objeto de departamento que se desea crear", required = true)
            @RequestBody @Valid final Departamento entidad) throws Exception;

    @Operation(summary = "Modifica un departamento", description = "Devuelve el departamento modificado")
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Departamento> modificar(
            @Parameter(description = "ID del departamento", required = true)
            @PathVariable("id") @Min(1) final Long id,
            @NotNull
            @Parameter(description = "Objeto del departamento con los nuevos datos a modificar", required = true)
            @RequestBody @Valid final Departamento entidad) throws Exception;

    @Operation(summary = "Busca un departamento por ID", description = "Retorna un único departamento")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/vnd.api+json"})
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Departamento> buscarPorId(
            @Parameter(description = "ID del departamento", required = true)
            @PathVariable("id") @Min(1) final Long id) throws Exception;

    @Operation(summary = "Busca todos los departamento", description = "Retorna todos los departamentos")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Collection<Departamento>> buscarTodos() throws Exception;

}