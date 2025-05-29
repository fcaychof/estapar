package com.caycho.estapar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Sector {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private double basePrice;
  private int maxCapacity;
  private LocalTime openHour;
  private LocalTime closeHour;

  @OneToMany(mappedBy = "sector")
  private List<Spot> spots;
}