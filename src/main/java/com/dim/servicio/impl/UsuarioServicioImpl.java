package com.dim.servicio.impl;

import com.dim.dominio.entidad.Usuario;
import com.dim.repositorio.UsuarioRepositorio;
import com.dim.servicio.interfaz.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public Usuario guardar(Usuario entidad) {
        return null;
    }

    @Override
    public Usuario actualizar(Long id, Usuario entidad) {
        return usuarioRepositorio.findById(id).map(u -> usuarioRepositorio.save(entidad)).orElseThrow();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return null;
    }

    @Override
    public Collection<Usuario> buscarTodos() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Long cantidad() {
        return usuarioRepositorio.count();
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    //eliminarUsuarioPorIdYPuerto
    @Override
    public boolean eliminarPorPuertoYidUsuario(Long puerto, Long id_usuario) {
        return usuarioRepositorio.eliminarUsuarioPorIdYPuerto(puerto, id_usuario);
    }



    @Override
    public boolean existePorId(Long id) {
        return usuarioRepositorio.existsById(id);
    }

    @Override
    public Collection<Usuario> buscarPorNumAfiliado(Long numAfiliado) {
        return usuarioRepositorio.findByNumAfiliado(numAfiliado);
    }
}
