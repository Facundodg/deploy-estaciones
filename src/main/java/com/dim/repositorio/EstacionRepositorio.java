package com.dim.repositorio;

import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {

    @Query(value = "select * from fn_buscar_datos_generales()", nativeQuery = true)
    Collection<EstacionPropiedades> buscarEstacionesPropiedades();

    List<Usuario> finByNumeroAfiliado(long numafiliado); //prueba

}
