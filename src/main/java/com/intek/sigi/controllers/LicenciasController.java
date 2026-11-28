package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LicenciasController {

    @GetMapping("/licencias")
    public String licencias() {
        return "licencias";
    }
}