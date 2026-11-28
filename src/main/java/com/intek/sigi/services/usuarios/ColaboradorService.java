
package com.intek.sigi.services.usuarios;

import com.intek.sigi.entities.Colaborador;
import com.intek.sigi.repositories.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public Optional<Colaborador> buscarPorDocumento(String documento) {
        return colaboradorRepository.findByDocumento(documento);
    }
}
