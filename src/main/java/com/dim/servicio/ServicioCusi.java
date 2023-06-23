package com.dim.servicio;

import com.dim.entidad.Usuario;
import com.dim.repositorio.InterfaseCusi;
import com.dim.repositorio.InterfaseUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCusi {

    @Autowired
    private InterfaseCusi interfaseCusi;

    public Usuario saveCusi(Usuario usuario){
        return interfaseCusi.save(usuario);
    }

    public Optional<Usuario> findByIdCusi(Long id){
        return interfaseCusi.findById(id);
    }


    public List<Usuario> findAllCusi(){
        return interfaseCusi.findAll();
    }


    public void deleteCusi(Long id){
        interfaseCusi.deleteById(id);
    }

    public boolean existByIdCusi(Long id) {
        return interfaseCusi.existsById(id);
    }

}
