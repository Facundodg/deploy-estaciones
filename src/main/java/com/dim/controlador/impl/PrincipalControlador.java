package com.dim.controlador.impl;

import com.dim.controlador.interfaz.PrincipalApi;
import com.dim.entidad.Departamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PrincipalControlador implements PrincipalApi {


    @Override
    public ResponseEntity<Departamento> buscarTodos() throws Exception {
        log.info("[PrincipalControlar - BuscarTodos]");
        return null;
    }
}
