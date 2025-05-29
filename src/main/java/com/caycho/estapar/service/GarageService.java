package com.caycho.estapar.service;

import com.caycho.estapar.controller.request.WebhookEventRequest;
import com.caycho.estapar.controller.response.RevenueResponse;
import com.caycho.estapar.controller.response.SpotStatusResponse;
import com.caycho.estapar.controller.response.VehicleStatusResponse;
import com.caycho.estapar.model.Vehicle;
import com.caycho.estapar.repository.SpotRepository;
import com.caycho.estapar.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageService {

  private final PricingService pricingService;
  private final VehicleRepository vehicleRepository;
  private final SpotRepository spotRepository;

  public void handleEvent(WebhookEventRequest event) {
    switch (event.eventType()) {
      case "ENTRY":
        handleEntry(event);
        break;
      case "PARKED":
        handleTransition(event, Boolean.TRUE, LocalDateTime.now(), null);
        break;
      case "EXIT":
        handleTransition(event, Boolean.FALSE, null, LocalDateTime.now());
        break;
    }
  }

  private void handleEntry(WebhookEventRequest event) {
    Vehicle vehicle = new Vehicle();
    vehicle.setLicensePlate(event.licensePlate());
    vehicle.setEntryTime(LocalDateTime.now());
    vehicleRepository.save(vehicle);
  }

  private void handleTransition(WebhookEventRequest event, boolean isParked, LocalDateTime entryTime,
      LocalDateTime exitTime) {
    Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(event.licensePlate());
    if (vehicle.isPresent()) {
      Vehicle vehicleSave = vehicle.get();
      vehicleSave.setEntryTime(entryTime);
      vehicleSave.setExitTime(exitTime);
      vehicleRepository.save(vehicleSave);
      spotRepository.findByLatAndLng(event.lat(), event.lng())
          .ifPresent(spot -> {
            spot.setOccupied(isParked);
            spotRepository.save(spot);
          });
    } else {
      throw new IllegalArgumentException("Vehicle not found for the given license plate.");
    }
  }

  public VehicleStatusResponse getVehicleStatus(String licensePlate) {
    Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(licensePlate);

    if (vehicle.isPresent()) {
      return VehicleStatusResponse.builder()
          .licensePlate(vehicle.get().getLicensePlate())
          .entryTime(vehicle.get().getEntryTime())
          .timeParked(
              String.valueOf(java.time.Duration.between(vehicle.get().getEntryTime(), LocalDateTime.now())))
          //.priceUntilNow(pricingService.calculateDynamicPrice())
          .lat(vehicle.get().getSpot().getLat())
          .lng(vehicle.get().getSpot().getLng())
          .build();
    } else {
      throw new IllegalArgumentException("Vehicle not found for the given license plate.");
    }
  }

  public SpotStatusResponse getSpotStatus(double lat, double lng) {
    // Spot spot = spotRepository.findByLatAndLng(lat, lng);
    // return new SpotStatus(spot);
    return null;
  }

  public RevenueResponse getRevenue(String date, String sector) {
    // List<Transaction> transactions = transactionRepository.findByDateAndSector(date, sector);
    //   double amount = transactions.stream().mapToDouble(Transaction::getPrice).sum();
    // return new RevenueResponse(amount, "BRL", LocalDateTime.now());
    return null;
  }
}
