package com.dim.servicio.impl;

import com.dim.entidad.Usuario;
import com.dim.repositorio.InterfaseUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {

    @Autowired
    private InterfaseUsuario interfaseUsuario;

    public Usuario saveUsuario(Usuario usuario){
        return interfaseUsuario.save(usuario);
    }

    public Optional<Usuario> findByIdUsuario(Long id){
        return interfaseUsuario.findById(id);
    }


    public List<Usuario> findAllUsuario(){
        return interfaseUsuario.findAll();
    }


    public void deleteUsuario(Long id){
        interfaseUsuario.deleteById(id);
    }

    public boolean existByIdUsuario(Long id) {
        return interfaseUsuario.existsById(id);
    }

}
