package com.dim.controlador.impl;

import com.dim.controlador.interfaz.DepartamentoApi;
import com.dim.dominio.entidad.Departamento;
import com.dim.servicio.interfaz.DepartamentoServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartamentoControlador implements DepartamentoApi {

    private final DepartamentoServicio departamentoServicio;

    @Override
    public ResponseEntity<Departamento> guardar(Departamento entidad) throws Exception {
        log.info(("[DepartamentoControladro - Guardar: Iniciada con departamento={}"), entidad.getNombreDepartamento());
        return ResponseEntity.ok(departamentoServicio.guardar(entidad));
    }

    @Override
    public ResponseEntity<Departamento> modificar(Long id, Departamento entidad) throws Exception {
        log.info(("[DepartamentoControlador - Modificar: Iniciada con id_departamento={}"), entidad.getIdDepartamento());
        return ResponseEntity.ok(departamentoServicio.guardar(entidad));
    }

    @Override
    public ResponseEntity<Departamento> buscarPorId(Long id) throws Exception {
        log.info("[EstacionControlador - BuscarPorId: Iniciada con id_estacion={}]", id);
        return ResponseEntity.ok(departamentoServicio.buscarPorId(id));
    }

    @Override
    public ResponseEntity<Collection<Departamento>> buscarTodos() throws Exception {
        log.info("[DepartamentoControlador - BuscarTodos]");
        return ResponseEntity.ok(departamentoServicio.buscarTodos());
    }
}
