package com.caycho.estapar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Vehicle {
  @Id
  private String licensePlate;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
  private double totalPrice;

  @ManyToOne
  private Spot spot;

  @ManyToOne
  private Sector sector;
}