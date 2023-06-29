package com.dim.controlador.impl;

import com.dim.controlador.interfaz.PrincipalApi;
import com.dim.dominio.dto.BuscadorDto;
import com.dim.dominio.entidad.Departamento;
import com.dim.dominio.entidad.Respuestas;
import com.dim.repositorio.RespuestaRepo;
import com.dim.servicio.impl.ServicioCusi;
import com.dim.servicio.impl.ServicioUsuario;
import com.dim.servicio.interfaz.EstacionServicio;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class PrincipalControlador implements PrincipalApi {

    private final EstacionServicio estacionServicio;
    private final ServicioCusi servicioCusi;
    private final ServicioUsuario servicioUsuario;

    //---------------------PRUEBA------------------------

            @Autowired
            private RespuestaRepo repositorio;

            @GetMapping("/getEstaciones")
            public List<Respuestas> obtenerResultadoProcedimiento() {
                return repositorio.ejecutarProcedimiento();
            }

            @GetMapping("/getEstacionesPorDepartamento/{depto}")
            public List<Respuestas> obtenerResultadoProcedimiento(@PathVariable("depto") long depto) {
                return repositorio.ejecutarProcedimientoPorDepto(depto);
            }


    //---------------------------------------------------

    @Override
    public ResponseEntity<Departamento> buscarTodos() throws Exception {
        log.info("[PrincipalControlar - BuscarTodos]");
        return null;
    }

    @GetMapping
    @Operation(summary = "Buscar por Cusi o Puerto Estacion o Usuario")
    public ResponseEntity<?> findByIdUsuario(@RequestBody BuscadorDto buscadorCusiPuertoUsuario) {

        // 1 - CUSI
        if (buscadorCusiPuertoUsuario.getBuscarPor().equals("1")) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorCusi(Long.valueOf(buscadorCusiPuertoUsuario.getBuscar())));

            // 2 - PUERTO
        } else if (buscadorCusiPuertoUsuario.getBuscarPor().equals("2")) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorPuerto(Long.valueOf(buscadorCusiPuertoUsuario.getBuscar())));

            // 3 - USUARIO

        } else if (buscadorCusiPuertoUsuario.getBuscarPor().equals("3")) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorUsuario(Long.parseLong(buscadorCusiPuertoUsuario.getBuscar())));


        } else {

            return null;

        }


    }


}
