package com.dim.repositorio;

import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Collection<Usuario> findByNumAfiliado(final long numafiliado);
}
