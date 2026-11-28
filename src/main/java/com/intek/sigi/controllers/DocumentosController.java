package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentosController {

    @GetMapping("/documentos")
    public String documentos() {
        return "documentos";
    }
}