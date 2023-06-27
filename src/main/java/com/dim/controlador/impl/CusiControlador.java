package com.dim.controlador.impl;

import com.dim.dominio.entidad.Cusi;
import com.dim.servicio.impl.CusiServicioImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/cusi")
@RequiredArgsConstructor
public class CusiControlador {

    private final CusiServicioImpl servicioCusi;

    @PostMapping
    @Operation(summary = "Guardar Cusi")
    public ResponseEntity<Cusi> guardar(@RequestBody Cusi usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCusi.guardar(usuario));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Guardar Cusi")
    public ResponseEntity eliminar(@PathVariable("id") Long id) {
        servicioCusi.eliminar(id);
        return ResponseEntity.ok(!servicioCusi.existePorId(id));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Cusi> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Cusi>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.buscarTodos());
    }
}
