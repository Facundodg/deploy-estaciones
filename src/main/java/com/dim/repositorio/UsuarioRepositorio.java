package com.dim.repositorio;

import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Collection<Usuario> findByNumAfiliado(final long numafiliado);

    @Query(value = "select * from fn_borrar_usuario(:puerto, :id_usuario)", nativeQuery = true)
    boolean eliminarUsuarioPorIdYPuerto( @Param("puerto") Long puerto, @Param("id_usuario") Long id_usuario);


}
