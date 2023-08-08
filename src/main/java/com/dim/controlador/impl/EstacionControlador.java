package com.dim.controlador.impl;

import com.dim.controlador.interfaz.EstacionApi;
import com.dim.dominio.entidad.Estacion;
import com.dim.servicio.interfaz.EstacionServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EstacionControlador implements EstacionApi {

    private final EstacionServicio estacionServicio;

    @Override
    public ResponseEntity<Estacion> guardar(Estacion entidad) throws Exception {
        log.info(("[EstacionControlador - Guardar: Iniciada con estacion={}"), entidad);
        return ResponseEntity.ok(estacionServicio.guardar(entidad));
    }

    @Override
    public ResponseEntity<Estacion> modificar(Long id, Estacion entidad) throws Exception {
        log.info(("[EstacionControlador - Modificar: Iniciada con id_estacion={}"), entidad.getIdStacion());
        return ResponseEntity.ok(estacionServicio.guardar(entidad));
    }

    @Override
    public ResponseEntity<Estacion> buscarPorId(Long id) throws Exception {
        log.info("[EstacionControlador - BuscarPorId: Iniciada con id_estacion={}]", id);
        return ResponseEntity.ok(estacionServicio.buscarPorId(id));
    }

    @Override
    public ResponseEntity<Collection<Estacion>> buscarTodos() throws Exception {
        log.info("[EstacionControlador - BuscarTodos]");
        return ResponseEntity.ok(estacionServicio.buscarTodos());
    }

    @Override
    public Boolean eliminarEstacion(Long puerto) throws Exception {
        log.info("[EstacionControlador - EliminaEstacionPorPuerto]");
        return estacionServicio.eliminarPorPuerto(puerto);
    }
}