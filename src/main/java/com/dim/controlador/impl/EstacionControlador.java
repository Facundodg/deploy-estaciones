package com.dim.controlador.impl;

import com.dim.controlador.interfaz.EstacionApi;
import com.dim.entidad.Estacion;
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

    private EstacionServicio estacionServicio;


    @Override
    public ResponseEntity<Estacion> guardar(Estacion entidad) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Estacion> modificar(Long id, Estacion entidad) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Estacion> buscarPorId(Long id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Collection<Estacion>> buscarTodos() throws Exception {
        return null;
    }
}
