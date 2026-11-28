package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GestionCobrosController {

    @GetMapping("/gestion-cobros")
    public String gestionCobros() {
        return "gestion-cobros";
    }
}