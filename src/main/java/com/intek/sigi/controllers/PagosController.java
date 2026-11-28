package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagosController {

    @GetMapping("/pagos")
    public String pagos() {
        return "pagos";
    }
}