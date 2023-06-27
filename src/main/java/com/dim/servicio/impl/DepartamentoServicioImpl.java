package com.dim.servicio.impl;

import com.dim.dominio.entidad.Departamento;
import com.dim.repositorio.DepartamentoRepositorio;
import com.dim.servicio.interfaz.DepartamentoServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartamentoServicioImpl implements DepartamentoServicio {

    private final DepartamentoRepositorio departamentoRepositorio;

    @Override
    public Departamento guardar(Departamento entidad) {
        return departamentoRepositorio.save(entidad);
    }

    @Override
    public Departamento actualizar(Long id, Departamento entidad) {
        return departamentoRepositorio.findById(id).map(u -> departamentoRepositorio.save(entidad)).orElseThrow();
    }

    @Override
    public Departamento buscarPorId(Long id) {
        return departamentoRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Collection<Departamento> buscarTodos() {
        return departamentoRepositorio.findAll();
    }

    @Override
    public Long cantidad() {
        return departamentoRepositorio.count();
    }

    @Override
    public void eliminar(Long id) {
        departamentoRepositorio.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return departamentoRepositorio.existsById(id);
    }
}
