package com.dim.controlador.impl;

import com.dim.controlador.interfaz.PrincipalApi;
import com.dim.dominio.dto.general.ConjuntoAlta;
import com.dim.dominio.dto.general.BuscarEstacion;
import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.dto.usuario.UsuarioAlta;
import com.dim.dominio.entidad.*;
import com.dim.repositorio.RespuestaRepo;
import com.dim.servicio.interfaz.CusiServicio;
import com.dim.servicio.interfaz.DepartamentoServicio;
import com.dim.servicio.interfaz.EstacionServicio;
import com.dim.servicio.interfaz.UsuarioServicio;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.io.NotActiveException;
import java.util.*;

import static com.dim.dominio.enumeracion.TipoBusqueda.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PrincipalControlador implements PrincipalApi {

    private final EstacionServicio estacionServicio;
    private final CusiServicio servicioCusi;
    private final UsuarioServicio servicioUsuario;
    private final DepartamentoServicio departamentoServicio;
    private final ModelMapper modelMapper;
    private final RespuestaRepo repositorio;
    private final RespuestaRepo verMasPuerto;

    private final RespuestaRepo verMasusuario;


    @GetMapping("/getEstaciones")
    public List<Respuestas> obtenerResultadoProcedimiento() {
        return repositorio.ejecutarProcedimiento();
    }

    @GetMapping("/getEstacionesPorDepartamento/{depto}")
    public List<Respuestas> obtenerResultadoProcedimiento(@PathVariable("depto") long depto) {
        return repositorio.ejecutarProcedimientoPorDepto(depto);
    }

    @GetMapping("/getVerMasEstacionPorPuerto/{puerto}")
    public List<Puerto> verMasPorPuerto(@PathVariable("puerto") long puerto) {
        return verMasPuerto.VerMasPorPuerto(puerto);
    }

    @GetMapping("/getUsuario/{id_usuario}")
    public List<DataUsuario> verMasUsuario(@PathVariable("id_usuario") long id_usuario) {
        return verMasusuario.SobreUsuario(id_usuario);
    }

    @Override
    public ResponseEntity<?> filtrarBusqueda(BuscarEstacion buscarEstacion) throws Exception {
        final long idBuscado = Long.parseLong(buscarEstacion.getBuscar());

        log.info("[PrincipalControlar - FiltrarBúsqueda: Iniciada por id_busqueda={}]", idBuscado);

        switch (buscarEstacion.getBuscarPor()){
            case CUSI -> {
                return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorCusi(idBuscado));
            }
            case USUARIO -> {
                return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorUsuario(idBuscado));
            }
            case PUERTO -> {
                return ResponseEntity.status(HttpStatus.OK).body(repositorio.ejecutarProcedimientoPorPuerto(idBuscado));
            }
            default -> throw new NotFoundException("[No es válida el tipo de búsqueda, comprobar el campo BuscarPor]");
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
        final Collection<UsuarioAlta> usuarios = conjuntoAlta.getUsuario();

        final Departamento departamento = departamentoServicio.buscarPorId(conjuntoAlta.getDepartamento());

        final Collection<Usuario> usuariosFinales = new ArrayList<>();
        for (UsuarioAlta usuario : usuarios) {
            usuariosFinales.add(modelMapper.map(usuario, Usuario.class));
        }

        if (cusi != null) {
            estacion.setCusi(cusi);
        }

        if (!usuarios.isEmpty()) {
            estacion.setUsuarios(new HashSet<Usuario>(usuariosFinales));
        }

        if (monitor != null) {
            estacion.setMonitor(monitor);
        }

        if(departamento != null){
            estacion.setDepartamento(departamento);
        }

        return ResponseEntity.ok().body(modelMapper.map(estacionServicio.guardar(estacion), EstacionPropiedades.class));
    }

    public ResponseEntity<EstacionPropiedades> modificarEstacionCompleta(ConjuntoAlta conjuntoAlta) throws Exception {
        log.info("[PrincipalControlador - ModificarEstacionCompleta: Iniciada con {}]", conjuntoAlta);

        final Estacion estacion = modelMapper.map(conjuntoAlta.getEstacion(), Estacion.class);
        final Cusi cusi = modelMapper.map(conjuntoAlta.getCusi(), Cusi.class);
        final Monitor monitor = modelMapper.map(conjuntoAlta.getMonitor(), Monitor.class);
        final Collection<UsuarioAlta> usuarios = conjuntoAlta.getUsuario();

        final Departamento departamento = departamentoServicio.buscarPorId(conjuntoAlta.getDepartamento());

        final Collection<Usuario> usuariosFinales = new ArrayList<>();
        for (UsuarioAlta usuario : usuarios) {
            usuariosFinales.add(modelMapper.map(usuario, Usuario.class));
        }

        if (cusi != null) {
            estacion.setCusi(cusi);
        }

        if (!usuarios.isEmpty()) {
            estacion.setUsuarios(new HashSet<Usuario>(usuariosFinales));
        }

        if (monitor != null) {
            estacion.setMonitor(monitor);
        }

        if(departamento != null){
            estacion.setDepartamento(departamento);
        }

        return ResponseEntity.ok().body(modelMapper.map(estacionServicio.guardar(estacion), EstacionPropiedades.class));
    }


}