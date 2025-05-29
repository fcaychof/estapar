package com.caycho.estapar.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

  public Double calculateDynamicPrice(Double basePrice, Integer currentCapacity, Integer maxCapacity) {
    double occupancyRate = (double) currentCapacity / maxCapacity;
    if (occupancyRate < 0.25) {
      return basePrice * 0.9;
    } else if (occupancyRate < 0.5) {
      return basePrice;
    } else if (occupancyRate < 0.75) {
      return basePrice * 1.1;
    } else {
      return basePrice * 1.25;
    }
  }
}
