package com.dim.repositorio;

import com.dim.dominio.entidad.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {

    @Query(value = "select *  fn_buscar_datos_generales", nativeQuery = true)
    Collection<Estacion> buscarDatosGenerales();
}
