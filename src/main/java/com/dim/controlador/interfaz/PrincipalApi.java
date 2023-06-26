package com.dim.controlador.interfaz;

import com.dim.entidad.Departamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@RequestMapping("/")
@Tag(name = "Principal", description = "Principal API")
public interface PrincipalApi {


    @Operation(summary = "Búsqueda general", description = "Retorna estaciones, departamentos y más informarción")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Departamento> buscarTodos() throws Exception;
}
