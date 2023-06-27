package com.dim.servicio.impl;

import com.dim.dominio.entidad.Cusi;
import com.dim.repositorio.CusiRepositorio;
import com.dim.servicio.interfaz.CusiServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CusiServicioImpl implements CusiServicio {

    private final CusiRepositorio cusiRepositorio;

    @Override
    public Cusi guardar(Cusi usuario) {
        return cusiRepositorio.save(usuario);
    }

    @Override
    public Cusi actualizar(Long id, Cusi entidad) {
        return cusiRepositorio.findById(id).map(u -> cusiRepositorio.save(entidad)).orElseThrow();
    }

    @Override
    public Cusi buscarPorId(Long id) {
        return cusiRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Collection<Cusi> buscarTodos() {
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
