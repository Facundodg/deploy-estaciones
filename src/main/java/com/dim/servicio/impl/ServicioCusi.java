package com.dim.servicio.impl;

import com.dim.dominio.entidad.Cusi;
import com.dim.repositorio.CusiRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCusi {

    @Autowired
    private CusiRepositorio cusiRepositorio;

    public Cusi saveCusi(Cusi usuario){
        return cusiRepositorio.save(usuario);
    }

    public Cusi findByIdCusi(Long id){
        return cusiRepositorio.findById(id).orElseThrow();
    }


    public List<Cusi> findAllCusi(){
        return cusiRepositorio.findAll();
    }


    public void deleteCusi(Long id){
        cusiRepositorio.deleteById(id);
    }

    public boolean existByIdCusi(Long id) {
        return cusiRepositorio.existsById(id);
    }

}
