package com.dim.servicio.impl;

import com.dim.dominio.entidad.Monitor;
import com.dim.repositorio.MonitorRepositorio;
import com.dim.servicio.interfaz.MonitorServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonitorServicioImpl implements MonitorServicio {

    private final MonitorRepositorio cusiRepositorio;

    @Override
    public Monitor guardar(Monitor usuario) {
        return cusiRepositorio.save(usuario);
    }

    @Override
    public Monitor actualizar(Long id, Monitor entidad) {
        return cusiRepositorio.findById(id).map(u -> cusiRepositorio.save(entidad)).orElseThrow();
    }

    @Override
    public Monitor buscarPorId(Long id) {
        return cusiRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Collection<Monitor> buscarTodos() {
        return cusiRepositorio.findAll();
    }

    @Override
    public Long cantidad() {
        return cusiRepositorio.count();
    }

    @Override
    public void eliminar(Long id) {
        cusiRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return cusiRepositorio.existsById(id);
    }

}
