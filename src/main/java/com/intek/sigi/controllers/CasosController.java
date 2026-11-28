package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CasosController {

    @GetMapping("/casos")
    public String casos() {
        return "gestion-cobros";
    }
}