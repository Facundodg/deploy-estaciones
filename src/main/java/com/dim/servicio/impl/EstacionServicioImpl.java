package com.dim.servicio.impl;

import com.dim.entidad.Estacion;
import com.dim.repositorio.EstacionRepositorio;
import com.dim.servicio.interfaz.EstacionServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstacionServicioImpl implements EstacionServicio {

    private final EstacionRepositorio estacionRepositorio;

    @Override
    public Estacion guardar(Estacion entidad) {
        return estacionRepositorio.save(entidad);
    }

    @Override
    public Estacion actualizar(Long id, Estacion entidad) {
        return estacionRepositorio.save(entidad);
    }

    @Override
    public Estacion buscarPorId(Long id) {
        return estacionRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Collection<Estacion> buscarTodos() {
        return estacionRepositorio.findAll();
    }

    @Override
    public Long cantidad() {
        return estacionRepositorio.count();
    }

    @Override
    public void eliminar(Long id) {
        estacionRepositorio.deleteById(id);
    }
}
