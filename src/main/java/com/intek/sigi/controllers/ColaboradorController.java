
package com.intek.sigi.controllers;

import com.intek.sigi.entities.Colaborador;
import com.intek.sigi.services.usuarios.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<Colaborador> buscarPorDocumento(
            @RequestParam String documento) {

        return colaboradorService.buscarPorDocumento(documento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
