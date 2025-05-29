package com.caycho.estapar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Garage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String sector;
  private Double basePrice;
  private Integer maxCapacity;
  private String openHour;
  private String closeHour;
  private Integer durationLimitMinutes;
}
