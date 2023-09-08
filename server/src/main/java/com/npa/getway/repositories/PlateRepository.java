package com.npa.getway.repositories;

import com.npa.getway.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlateRepository extends JpaRepository<Plate, Long> {
}
