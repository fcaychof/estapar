package com.caycho.estapar.controller;

import com.caycho.estapar.controller.request.SpotStatusRequest;
import com.caycho.estapar.controller.request.VehicleStatusRequest;
import com.caycho.estapar.controller.response.RevenueResponse;
import com.caycho.estapar.controller.response.SpotStatusResponse;
import com.caycho.estapar.controller.response.VehicleStatusResponse;
import com.caycho.estapar.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GarageController {

  private final GarageService garageService;

  @PostMapping("/plate-status")
  public ResponseEntity<VehicleStatusResponse> getPlateStatus(@RequestBody VehicleStatusRequest request) {
    return ResponseEntity.ok(garageService.getVehicleStatus(request.licensePlate()));
  }

  @PostMapping("/spot-status")
  public ResponseEntity<SpotStatusResponse> getSpotStatus(@RequestBody SpotStatusRequest request) {
    return ResponseEntity.ok(garageService.getSpotStatus(request.lat(), request.lng()));
  }

  @GetMapping("/revenue")
  public ResponseEntity<RevenueResponse> getRevenue(@RequestParam String date, @RequestParam String sector) {
    return ResponseEntity.ok(garageService.getRevenue(date, sector));
  }
}
