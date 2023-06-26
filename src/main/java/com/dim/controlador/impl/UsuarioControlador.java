package com.dim.controlador.impl;

import com.dim.entidad.Usuario;
import com.dim.servicio.impl.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private ServicioUsuario servicioUsuario;

    //listo
    @PostMapping
    public ResponseEntity<Usuario> GuardarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUsuario.saveUsuario(usuario));
    }

    //listo
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable ("id") Long id){
        servicioUsuario.deleteUsuario(id);
        return ResponseEntity.ok(!servicioUsuario.existByIdUsuario(id));
    }


    //listo
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> findByIdUsuario(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.findByIdUsuario(id));
    }

    //listo

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.findAllUsuario());
    }

    /*

    @PutMapping
    public ResponseEntity<Usuario> editStudent (@RequestBody Usuario estudiante_model){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUsuario.e(estudiante_model));
    }
     */

}
