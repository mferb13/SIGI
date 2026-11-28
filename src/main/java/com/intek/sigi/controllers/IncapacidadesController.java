
package com.intek.sigi.controllers;

import com.intek.sigi.repositories.TipoNovedadRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IncapacidadesController {

    private final TipoNovedadRepository tipoNovedadRepository;

    public IncapacidadesController(TipoNovedadRepository tipoNovedadRepository) {
        this.tipoNovedadRepository = tipoNovedadRepository;
    }

    @GetMapping("/incapacidades")
    public String registro(Model model) {

        model.addAttribute("tiposNovedad", tipoNovedadRepository.findAll());

        return "incapacidades/registro";
    }
}
