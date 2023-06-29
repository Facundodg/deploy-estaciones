package com.dim.controlador.impl;

import com.dim.controlador.interfaz.PrincipalApi;
import com.dim.dominio.dto.BusquedaDto;
import com.dim.dominio.dto.EstacionDto;
import com.dim.dominio.entidad.Respuestas;
import com.dim.dominio.enumeracion.Busqueda;
import com.dim.repositorio.RespuestaRepo;
import com.dim.servicio.impl.CusiServicioImpl;
import com.dim.servicio.impl.UsuarioServicioImpl;
import com.dim.servicio.interfaz.EstacionServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class PrincipalControlador implements PrincipalApi {

    private final EstacionServicio estacionServicio;
    private final CusiServicioImpl servicioCusi;
    private final UsuarioServicioImpl servicioUsuario;

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
    public ResponseEntity<?> filtrarBusqueda(BusquedaDto busquedaDto) throws Exception {
        final Long idBuscado = Long.parseLong(busquedaDto.getBuscar());

        log.info("[PrincipalControlar - FiltrarBúsqueda: Iniciada por id_busqueda={}]", idBuscado);

        if (busquedaDto.getBuscarPor() == Busqueda.CUSI) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorCusi(idBuscado));

        } else if (busquedaDto.getBuscarPor() == Busqueda.ESTACION) {

            return ResponseEntity.ok().body(estacionServicio.buscarPorId(idBuscado));

        } else if (busquedaDto.getBuscarPor() == Busqueda.PUERTO) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorPuerto(idBuscado));

        } else if (busquedaDto.getBuscarPor() == Busqueda.USUARIO) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorUsuario(idBuscado));

//            return ResponseEntity.ok().body(servicioUsuario.buscarPorNumAfiliado(idBuscado));

        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<Collection<EstacionDto>> busquedaPrincipal() throws Exception {
        log.info("[PrincipalControlador - BuscarDatosGenerales]");
        return ResponseEntity.ok(estacionServicio.buscarDatosGenerales());
    }
}