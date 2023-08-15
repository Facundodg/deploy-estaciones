package com.dim.repositorio;

import com.dim.dominio.entidad.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Slf4j
@Component
public class RespuestaRepo extends Conexion{

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
                puerto.setNum_cusi(resultSet.getLong("num_cusi"));

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
                dataUsuario.setNombre(resultSet.getString("nombre"));
                dataUsuario.setApellido(resultSet.getString("apellido"));
                dataUsuario.setIdDepartamento(resultSet.getLong("id_departamento"));

                return dataUsuario;
            }
        });}

    public boolean modificFACUNDO(Estacion estacion, Cusi cusi, Monitor monitor) {

        PreparedStatement ps = null;
        Connection con = getConnection(); // Obtener la conexión a la base de datos
        CallableStatement cs = null;

        // fn_modificar_estacion_completa(modificaciones modificacion_estaciones)

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            String estacionString = objectMapper.writeValueAsString(estacion);
            String cusiString = objectMapper.writeValueAsString(cusi);
            String monitorString = objectMapper.writeValueAsString(monitor);

            String sql = "{ call fn_modificar_estacion_completa(?::jsonb, ?::jsonb, ?::jsonb) }";
            CallableStatement callableStatement = con.prepareCall(sql);

            log.info("[estacion = {}]", estacionString);
            log.info("[cusi = {}]", cusiString);
            log.info("[monitor = {}]", monitorString);

            //callableStatement = con.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.BOOLEAN); // Registro del parámetro de retorno
            callableStatement.setString(2, estacionString);
            callableStatement.setString(3, cusiString);
            callableStatement.setString(4, monitorString);

            callableStatement.execute();

            boolean resultado = callableStatement.getBoolean(1);

            callableStatement.close();
            con.close();

            return resultado;


        } catch (SQLException e) {

            System.out.println(e);

            return false;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {

            try {

                con.close();

            } catch (SQLException e) {

                System.out.println(e);

            }
        }
    }

}























