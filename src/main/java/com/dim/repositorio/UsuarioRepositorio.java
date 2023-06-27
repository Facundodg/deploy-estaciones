package com.dim.repositorio;

import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query(value = "select * from usuario where num_afiliado = :numafiliado" , nativeQuery = true)
    Collection<Usuario> finByNumeroAfiliado(long numafiliado);
}
