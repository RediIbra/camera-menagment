package com.npa.getway.repositories;

import com.npa.getway.model.Webcam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WebcamRepository extends JpaRepository<Webcam, Long> {
    List<Webcam> findByCenterId(Long id);
}
