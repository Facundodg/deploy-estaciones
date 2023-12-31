package com.dim.repositorio;

import com.dim.dominio.dto.estacion.EstacionAltaModificar;
import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.dto.general.ConjuntoAltaModificar;
import com.dim.dominio.entidad.Cusi;
import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {

    @Query(value = "select * from fn_buscar_estaciones_propiedades()", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionesPropiedades();

    @Query(value = "select * from fn_buscar_estacion_por_puerto(?1)", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionPorPuerto(final long puerto);

    @Query(value = "select * from fn_buscar_estacion_por_usuario(?1)", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionPorUsuario(final long usuario);

    @Query(value = "select * from fn_buscar_estacion_por_departamento(?1)", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionesPorDepartamento(final long departamento);
    @Query(value = "select * from fn_buscar_estacion_por_usuario()", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionPorUsuario();

    @Query(value = "select * from fn_borrar_estacion(:puerto)", nativeQuery = true)
    Boolean borrarEstacionPorPuertoCompleta( @Param("puerto") Long puerto);

    boolean existsByPuerto(long puerto);


    @Query(value = "select * from fn_modificar_estacion_completa(:estacion,:cusi,:monitor)", nativeQuery = true)
    boolean modificarFacundo(@Param("estacion") Estacion estacion,@Param("cusi") Cusi cusi, @Param("monitor") Monitor monitor);

    Optional<Estacion> findByPuerto(long puerto);

}
