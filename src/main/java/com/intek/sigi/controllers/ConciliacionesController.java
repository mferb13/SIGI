package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConciliacionesController {

    @GetMapping("/conciliaciones")
    public String conciliaciones() {
        return "conciliaciones";
    }
}