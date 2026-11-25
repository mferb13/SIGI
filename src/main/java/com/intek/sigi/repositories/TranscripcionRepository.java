package com.intek.sigi.repositories;

import com.intek.sigi.entities.Transcripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscripcionRepository extends JpaRepository<Transcripcion, Long> {
}