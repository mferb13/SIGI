
package com.intek.sigi.repositories;

import com.intek.sigi.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    Optional<Colaborador> findByDocumento(String documento);

}
