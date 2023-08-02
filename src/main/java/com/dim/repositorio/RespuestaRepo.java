package com.dim.repositorio;

import com.dim.dominio.entidad.DataUsuario;
import com.dim.dominio.entidad.Puerto;
import com.dim.dominio.entidad.Respuestas;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class RespuestaRepo {

    private final JdbcTemplate jdbcTemplate;

    public RespuestaRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //LLAMA A PROCEDIMIENTO ALMACENADO QUE TRAE TODAS LAS ESTACIONES
    public List<Respuestas> ejecutarProcedimiento() {

        String sql = "select * from fn_buscar_estaciones_propiedades()";

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
                entidadResultado.setId_usuario(resultSet.getString("id_usuario"));

                return entidadResultado;
            }
        });
    }

    //LLAMA A LAS ESTACIONES RELACIONADAS A UN DEPARTAMENTO
    public List<Respuestas> ejecutarProcedimientoPorDepto(long numDepto) {

        String sql = "select * from fn_buscar_estacion_por_departamento("+numDepto+")";

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

        String sql = "select * from fn_buscar_estacion_por_cusi("+cusi+")";

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

        String sql = "select * from fn_buscar_estacion_por_puerto("+puerto+")";

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

        String sql = "select * from fn_buscar_estacion_por_usuario("+usuario+")";

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


    //LLAMA A LA ESTACION POR PUERTO (PUERTO) PERO ES PARA EL VER MAS DEL BOTON
    public List<Puerto> VerMasPorPuerto(long puerto) {

        String sql = "select * from fn_buscar_informacion_general_por_puerto("+puerto+")";

        return jdbcTemplate.query(sql, new RowMapper<Puerto>() {
            @Override
            public Puerto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                Puerto puerto = new Puerto();

                puerto.setId_cusi(resultSet.getLong("id_cusi"));
                puerto.setDisco(resultSet.getString("disco"));
                puerto.setDvd(resultSet.getBoolean("dvd"));
                puerto.setMicro(resultSet.getString("micro"));
                puerto.setMother(resultSet.getString("mother"));
                puerto.setMouse(resultSet.getBoolean("mouse"));
                puerto.setRam(resultSet.getString("ram"));
                puerto.setSo(resultSet.getString("so"));
                puerto.setTeclado(resultSet.getBoolean("teclado"));
                puerto.setHostname(resultSet.getString("hostname"));
                puerto.setId_monitor(resultSet.getLong("id_monitor"));
                puerto.setMarca(resultSet.getString("marca"));
                puerto.setModelo(resultSet.getString("modelo"));
                puerto.setNumero_serie(resultSet.getLong("numero_serie"));
                puerto.setId_estacion(resultSet.getLong("id_estacion"));
                puerto.setPuerto(resultSet.getLong("puerto"));

                return puerto;
            }
        });}


    //LLAMA A LA ESTACION POR PUERTO (PUERTO) PERO ES PARA EL VER MAS DEL BOTON
    public List<DataUsuario> SobreUsuario(long id_usuario) {

        String sql = "select * from fn_buscar_usuarios_propiedades("+id_usuario+")";

        return jdbcTemplate.query(sql, new RowMapper<DataUsuario>() {
            @Override
            public DataUsuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {

                DataUsuario dataUsuario = new DataUsuario();

                dataUsuario.setId_usuario(resultSet.getLong("id_usuario"));
                dataUsuario.setUsuario(resultSet.getString("usuario"));
                dataUsuario.setClave(resultSet.getString("clave"));

                return dataUsuario;
            }
        });}


    //fn_borrar_usuario(puerto,id_usuario)


}






















