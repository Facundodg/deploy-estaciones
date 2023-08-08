package com.dim.servicio.impl;

import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Respuestas;
import com.dim.repositorio.EstacionRepositorio;
import com.dim.servicio.interfaz.EstacionServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstacionServicioImpl implements EstacionServicio {
    private final EstacionRepositorio estacionRepositorio;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Estacion guardar(Estacion entidad) {
        return estacionRepositorio.save(entidad);
    }

    @Override
    public Estacion actualizar(Long id, Estacion entidad) {
        return estacionRepositorio.findById(id).map(u -> estacionRepositorio.save(entidad)).orElseThrow();
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

    @Override
    public boolean existePorId(Long id) {
        return estacionRepositorio.existsById(id);
    }

    @Override
    public Collection<EstacionPropiedades> buscarTodosConPropiedades() {
        return estacionRepositorio.buscarEstacionesPropiedades();
    }

    @Override
    public Collection<EstacionPropiedades> buscarEstacionesSimplificadas() {
        return null;
    }

    @Override
    public boolean existePorPuerto(Long puerto) {
        return estacionRepositorio.existsByPuerto(puerto);
    }

    @Override
    public boolean eliminarPorPuerto(Long puerto) {
        return estacionRepositorio.borrarEstacionPorPuertoCompleta(puerto);
    }


    //LLAMA A PROCEDIMIENTO ALMACENADO QUE TRAE TODAS LAS ESTACIONES
    public List<Respuestas> ejecutarProcedimiento() {

        String sql = "select * from mostrar()";

        return jdbcTemplate.query(sql, new RowMapper<Respuestas>() {
            @Override
            public Respuestas mapRow(ResultSet resultSet, int rowNum) throws SQLException {


                Respuestas entidadResultado = new Respuestas();
                entidadResultado.setNombre(resultSet.getString("nombre"));
                entidadResultado.setApellido(resultSet.getString("apellido"));
                entidadResultado.setPuerto(resultSet.getLong("puerto"));
                entidadResultado.setHostname(resultSet.getString("hostname"));
                entidadResultado.setNum_cusi(resultSet.getLong("num_cusi"));
                entidadResultado.setSo(resultSet.getString("so"));
                entidadResultado.setNombre_depto(resultSet.getString("nombre_depto"));

                return entidadResultado;
            }
        });
    }

    //LLAMA A LAS ESTACIONES RELACIONADAS A UN DEPARTAMENTO
    public Collection<EstacionPropiedades> buscarEstacionesPorDepartamento(final long numeroDepartamento) {
        return estacionRepositorio.buscarEstacionesPorDepartamento(numeroDepartamento);
    }

    //LLAMA A LA ESTACION POR CUSI (NUMERO DE CUSI)
    public List<Respuestas> ejecutarProcedimientoPorCusi(long cusi) {

        String sql = "select * from mostrarPorCusi(" + cusi + ")";

        return jdbcTemplate.query(sql, new RowMapper<Respuestas>() {
            @Override
            public Respuestas mapRow(ResultSet resultSet, int rowNum) throws SQLException {


                Respuestas entidadResultado = new Respuestas();
                entidadResultado.setNombre(resultSet.getString("nombre"));
                entidadResultado.setApellido(resultSet.getString("apellido"));
                entidadResultado.setPuerto(resultSet.getLong("puerto"));
                entidadResultado.setHostname(resultSet.getString("hostname"));
                entidadResultado.setNum_cusi(resultSet.getLong("num_cusi"));
                entidadResultado.setSo(resultSet.getString("so"));
                entidadResultado.setNombre_depto(resultSet.getString("nombre_depto"));

                return entidadResultado;
            }
        });
    }

    //LLAMA A LA ESTACION POR PUERTO (PUERTO)
    public List<Respuestas> ejecutarProcedimientoPorPuerto(long puerto) {

        String sql = "select * from mostrarPorPuerto(" + puerto + ")";

        return jdbcTemplate.query(sql, new RowMapper<Respuestas>() {
            @Override
            public Respuestas mapRow(ResultSet resultSet, int rowNum) throws SQLException {


                Respuestas entidadResultado = new Respuestas();
                entidadResultado.setNombre(resultSet.getString("nombre"));
                entidadResultado.setApellido(resultSet.getString("apellido"));
                entidadResultado.setPuerto(resultSet.getLong("puerto"));
                entidadResultado.setHostname(resultSet.getString("hostname"));
                entidadResultado.setNum_cusi(resultSet.getLong("num_cusi"));
                entidadResultado.setSo(resultSet.getString("so"));
                entidadResultado.setNombre_depto(resultSet.getString("nombre_depto"));

                return entidadResultado;
            }
        });
    }

    //LLAMA A LA ESTACION POR NUMERO DE AFILIADO DEL USUARIO

    public List<Respuestas> ejecutarProcedimientoPorUsuario(long usuario) {

        String sql = "select * from mostrarPorUsuario(" + usuario + ")";

        return jdbcTemplate.query(sql, new RowMapper<Respuestas>() {
            @Override
            public Respuestas mapRow(ResultSet resultSet, int rowNum) throws SQLException {


                Respuestas entidadResultado = new Respuestas();
                entidadResultado.setNombre(resultSet.getString("nombre"));
                entidadResultado.setApellido(resultSet.getString("apellido"));
                entidadResultado.setPuerto(resultSet.getLong("puerto"));
                entidadResultado.setHostname(resultSet.getString("hostname"));
                entidadResultado.setNum_cusi(resultSet.getLong("num_cusi"));
                entidadResultado.setSo(resultSet.getString("so"));
                entidadResultado.setNombre_depto(resultSet.getString("nombre_depto"));

                return entidadResultado;
            }
        });
    }

}
