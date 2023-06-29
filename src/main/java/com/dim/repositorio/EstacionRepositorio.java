package com.dim.repositorio;

import com.dim.dominio.dto.EstacionDto;
import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {

    @Query(value = "select fn_buscar_datos_generales()", nativeQuery = true)
    Collection<EstacionDto> buscarDatosGenerales();

    List<Usuario> finByNumeroAfiliado(long numafiliado); //prueba

}
