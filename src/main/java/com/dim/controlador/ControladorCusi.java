package com.dim.controlador;

import com.dim.entidad.Usuario;
import com.dim.servicio.ServicioCusi;
import com.dim.servicio.ServicioUsuario;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cusi")
public class ControladorCusi {

    @Autowired
    private ServicioCusi servicioCusi;

    //listo
    @PostMapping
    @Operation(summary = "Guardar Usuario")
    public ResponseEntity<Usuario> GuardarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCusi.saveCusi(usuario));
    }

    //listo
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Guardar Usuario")
    public ResponseEntity deleteStudent(@PathVariable("id") Long id){
        servicioCusi.deleteCusi(id);
        return ResponseEntity.ok(!servicioCusi.existByIdCusi(id));
    }


    //listo
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Usuario>> findByIdUsuario(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.findByIdCusi(id));
    }

    //listo

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(servicioCusi.findAllCusi());
    }

}
