package com.dim.controlador;

import com.dim.entidad.Monitor;
import com.dim.entidad.Usuario;
import com.dim.servicio.ServicioMonitor;
import com.dim.servicio.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monitor")
public class ControladorMonitor {

    @Autowired
    private ServicioMonitor servicioMonitor;

    //listo
    @PostMapping
    public ResponseEntity<Monitor> GuardarUsuario(@RequestBody Monitor monitor){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioMonitor.saveMonitor(monitor));
    }

    //listo
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable ("id") Long id){
        servicioMonitor.deleteMonitor(id);
        return ResponseEntity.ok(!servicioMonitor.existByIdMonitor(id));
    }


    //listo
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Monitor>> findByIdUsuario(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(servicioMonitor.findByIdMonitor(id));
    }

    //listo

    @GetMapping
    public ResponseEntity<List<Monitor>> findAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(servicioMonitor.findAllMonitor());
    }


}
