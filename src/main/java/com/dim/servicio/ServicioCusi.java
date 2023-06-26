package com.dim.servicio;

import com.dim.entidad.Cusi;
import com.dim.repositorio.InterfaseCusi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCusi {

    @Autowired
    private InterfaseCusi interfaseCusi;

    public Cusi saveCusi(Cusi usuario){
        return interfaseCusi.save(usuario);
    }

    public Optional<Cusi> findByIdCusi(Long id){
        return interfaseCusi.findById(id);
    }


    public List<Cusi> findAllCusi(){
        return interfaseCusi.findAll();
    }


    public void deleteCusi(Long id){
        interfaseCusi.deleteById(id);
    }

    public boolean existByIdCusi(Long id) {
        return interfaseCusi.existsById(id);
    }

}
