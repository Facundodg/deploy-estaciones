package com.dim.servicio.impl;

import com.dim.dominio.entidad.Monitor;
import com.dim.repositorio.MonitorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMonitor {

    @Autowired
    private MonitorRepositorio monitorRepositorio;

    public Monitor saveMonitor(Monitor monitor){
        return monitorRepositorio.save(monitor);
    }

    public Optional<Monitor> findByIdMonitor(Long id){
        return monitorRepositorio.findById(id);
    }


    public List<Monitor> findAllMonitor(){
        return monitorRepositorio.findAll();
    }


    public void deleteMonitor(Long id){
        monitorRepositorio.deleteById(id);
    }

    public boolean existByIdMonitor(Long id) {
        return monitorRepositorio.existsById(id);
    }


}
