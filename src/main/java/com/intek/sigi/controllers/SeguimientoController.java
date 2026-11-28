package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguimientoController {

    @GetMapping("/seguimiento")
    public String seguimiento() {
        return "seguimiento";
    }
}