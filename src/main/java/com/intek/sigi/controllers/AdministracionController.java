package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministracionController {

    @GetMapping("/administracion")
    public String administracion() {
        return "administracion";
    }
}