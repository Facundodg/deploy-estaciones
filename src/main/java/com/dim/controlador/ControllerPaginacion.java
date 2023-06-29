package com.dim.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerPaginacion {

    @RequestMapping("/home")
    public String abrirhome() {
        return "index";
    }

}
