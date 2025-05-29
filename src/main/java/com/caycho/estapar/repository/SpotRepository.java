package com.caycho.estapar.repository;

import com.caycho.estapar.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpotRepository extends JpaRepository<Spot, Long> {
  Optional<Spot> findByLatAndLng(Double lat, Double lng);
}
