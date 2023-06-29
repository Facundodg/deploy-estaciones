package com.dim.repositorio;

import com.dim.dominio.entidad.Respuestas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class RespuestaRepo {

    private final JdbcTemplate jdbcTemplate;

    public RespuestaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public List<Respuestas> ejecutarProcedimientoPorDepto(long numDepto) {

        String sql = "select * from mostrarPorDepto("+numDepto+")";

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

    //LLAMA A LA ESTACION POR CUSI (NUMERO DE CUSI)
    public List<Respuestas> ejecutarProcedimientoPorCusi(long cusi) {

        String sql = "select * from mostrarPorCusi("+cusi+")";

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

        String sql = "select * from mostrarPorPuerto("+puerto+")";

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
        });}

    //LLAMA A LA ESTACION POR NUMERO DE AFILIADO DEL USUARIO

    public List<Respuestas> ejecutarProcedimientoPorUsuario(long usuario) {

        String sql = "select * from mostrarPorUsuario("+usuario+")";

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
        });}


}
