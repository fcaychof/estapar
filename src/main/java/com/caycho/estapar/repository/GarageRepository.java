package com.caycho.estapar.repository;

import com.caycho.estapar.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, Long> {

  Garage findBySector(String sector);


}
