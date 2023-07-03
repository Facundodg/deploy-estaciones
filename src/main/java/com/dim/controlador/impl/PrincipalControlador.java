package com.dim.controlador.impl;

import com.dim.controlador.interfaz.PrincipalApi;
import com.dim.dominio.dto.general.ConjuntoAlta;
import com.dim.dominio.dto.general.BuscarEstacion;
import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.*;
import com.dim.repositorio.RespuestaRepo;
import com.dim.servicio.interfaz.CusiServicio;
import com.dim.servicio.interfaz.EstacionServicio;
import com.dim.servicio.interfaz.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.dim.dominio.enumeracion.TipoBusqueda.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PrincipalControlador implements PrincipalApi {

    private final EstacionServicio estacionServicio;
    private final CusiServicio servicioCusi;
    private final UsuarioServicio servicioUsuario;

    private final ModelMapper modelMapper;

    private final RespuestaRepo repositorio;

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
    public ResponseEntity<?> filtrarBusqueda(BuscarEstacion buscarEstacion) throws Exception {
        final Long idBuscado = Long.parseLong(buscarEstacion.getBuscar());

        log.info("[PrincipalControlar - FiltrarBúsqueda: Iniciada por id_busqueda={}]", idBuscado);

        if (buscarEstacion.getBuscarPor() == CUSI) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorCusi(idBuscado));

        } else if (buscarEstacion.getBuscarPor() == PUERTO) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorPuerto(idBuscado));

        } else if (buscarEstacion.getBuscarPor() == USUARIO) {

            return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorUsuario(idBuscado));

        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<Collection<EstacionPropiedades>> buscarEstacionesPropiedades() throws Exception {
        log.info("[PrincipalControlador - BuscarDatosGenerales]");
        return ResponseEntity.ok(estacionServicio.buscarEstacionesSimplificadas());
    }

    public ResponseEntity<EstacionPropiedades> cargarEstacionCompleta(ConjuntoAlta conjuntoAlta) throws Exception {
        log.info("[PrincipalControlador - CargarEstacionCompleta: Iniciada con {}]", conjuntoAlta);

        final Estacion estacion = modelMapper.map(conjuntoAlta.getEstacion(), Estacion.class);
        final Cusi cusi = modelMapper.map(conjuntoAlta.getCusi(), Cusi.class);
        final Monitor monitor = modelMapper.map(conjuntoAlta.getMonitor(), Monitor.class);
        final Usuario usuario = modelMapper.map(conjuntoAlta, Usuario.class);

        if (cusi != null) {
            estacion.setCusi(cusi);
        }

        if (usuario != null) {
            estacion.setUsuarios(Set.of(usuario));
        }

        if (monitor != null) {
            estacion.setMonitor(monitor);
        }

        return ResponseEntity.ok().body(modelMapper.map(estacionServicio.guardar(estacion), EstacionPropiedades.class));
    }

}