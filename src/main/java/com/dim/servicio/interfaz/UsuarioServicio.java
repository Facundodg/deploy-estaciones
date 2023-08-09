package com.dim.servicio.interfaz;

import com.dim.dominio.entidad.Usuario;

import java.util.Collection;

public interface UsuarioServicio extends ServicioCrud<Usuario>{

    //eliminarUsuarioPorIdYPuerto
    boolean eliminarPorPuertoYidUsuario(Long puerto, Long id_usuario);

    Collection<Usuario> buscarPorNumAfiliado(final Long numAfiliado);
}
