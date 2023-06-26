package com.dim.controlador.impl;

import com.dim.entidad.Cusi;
import com.dim.servicio.impl.ServicioCusi;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cusi")
public class CusiControlador {

    @Autowired
    private ServicioCusi servicioCusi;

    //listo
    @PostMapping
    @Operation(summary = "Guardar Cusi")
    public ResponseEntity<Cusi> GuardarCusi(@RequestBody Cusi usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCusi.saveCusi(usuario));
    }

    //listo
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Guardar Cusi")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id){
        servicioCusi.deleteCusi(id);
        return ResponseEntity.ok(!servicioCusi.existByIdCusi(id));
    }


    //listo
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cusi> findByIdCusi(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.findByIdCusi(id));
    }

    //listo
    @GetMapping
    public ResponseEntity<List<Cusi>> findAllCusi(){
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.findAllCusi());
    }

}
