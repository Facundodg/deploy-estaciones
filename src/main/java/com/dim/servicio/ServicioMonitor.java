package com.dim.servicio;

import com.dim.entidad.Monitor;
import com.dim.repositorio.InterfaseMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMonitor {

    @Autowired
    private InterfaseMonitor interfaseMonitor;

    public Monitor saveMonitor(Monitor monitor){
        return interfaseMonitor.save(monitor);
    }

    public Optional<Monitor> findByIdMonitor(Long id){
        return interfaseMonitor.findById(id);
    }


    public List<Monitor> findAllMonitor(){
        return interfaseMonitor.findAll();
    }


    public void deleteMonitor(Long id){
        interfaseMonitor.deleteById(id);
    }

    public boolean existByIdMonitor(Long id) {
        return interfaseMonitor.existsById(id);
    }


}
