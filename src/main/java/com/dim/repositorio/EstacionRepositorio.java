package com.dim.repositorio;

import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {

    @Query(value = "Select * from public.estacion " , nativeQuery = true)
    List<Usuario> finByNumeroAfiliado(long numafiliado); //prueba

}
