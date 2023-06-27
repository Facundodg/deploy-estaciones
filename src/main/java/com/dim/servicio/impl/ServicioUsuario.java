package com.dim.servicio.impl;

import com.dim.dominio.entidad.Usuario;
import com.dim.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> findByIdUsuario(Long id){
        return usuarioRepositorio.findById(id);
    }


    public List<Usuario> findAllUsuario(){
        return usuarioRepositorio.findAll();
    }


    public void deleteUsuario(Long id){
        usuarioRepositorio.deleteById(id);
    }

    public boolean existByIdUsuario(Long id) {
        return usuarioRepositorio.existsById(id);
    }

    public List<Usuario> findByNumAfiliadoUsuario(long numAfiliado){

        return usuarioRepositorio.finByNumeroAfiliado(numAfiliado);

    }
}
