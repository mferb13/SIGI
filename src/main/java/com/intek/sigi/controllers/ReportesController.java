package com.intek.sigi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportesController {

    @GetMapping("/reportes")
    public String reportes() {
        return "reportes";
    }
}