package com.dim.servicio.interfaz;

import com.dim.dominio.entidad.Usuario;

import java.util.Collection;

public interface UsuarioServicio extends ServicioCrud<Usuario>{

    Collection<Usuario> buscarPorNumAfiliado(final Long numAfiliado);
}
