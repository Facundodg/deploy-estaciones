package com.dim.controlador.impl;

import com.dim.dominio.entidad.Monitor;
import com.dim.servicio.interfaz.MonitorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorControlador {

    private final MonitorServicio monitorServicio;

    //listo
    @PostMapping
    public ResponseEntity<Monitor> guardar(@RequestBody Monitor monitor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monitorServicio.guardar(monitor));
    }

    //listo
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        monitorServicio.eliminar(id);
        return ResponseEntity.ok(monitorServicio.existePorId(id));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Monitor> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(monitorServicio.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Monitor>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(monitorServicio.buscarTodos());
    }
}
