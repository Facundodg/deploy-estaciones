package com.dim.controlador.impl;

import com.dim.dominio.entidad.Usuario;
import com.dim.servicio.interfaz.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio servicioUsuario;

    //listo
    @PostMapping
    public ResponseEntity<Usuario> GuardarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUsuario.guardar(usuario));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity BorrarUsuario(@PathVariable ("id") Long id){
        servicioUsuario.eliminar(id);
        return ResponseEntity.ok(!servicioUsuario.existePorId(id));
    }

    @DeleteMapping(value = "/{puerto}/{id_usuario}")
    public ResponseEntity BorrarUsuarioPorPuertoIdusuario(@PathVariable ("puerto") Long puerto,@PathVariable ("id_usuario") Long id_usuario){
        servicioUsuario.eliminarPorPuertoYidUsuario(puerto,id_usuario);
        return ResponseEntity.ok(!servicioUsuario.existePorId(id_usuario));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Usuario>> findAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarTodos());
    }

    /*

    @PutMapping
    public ResponseEntity<Usuario> editStudent (@RequestBody Usuario estudiante_model){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUsuario.e(estudiante_model));
    }
     */

}
