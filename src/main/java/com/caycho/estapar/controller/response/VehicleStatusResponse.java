package com.caycho.estapar.controller.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record VehicleStatusResponse(
    String licensePlate,
    Double priceUntilNow,
    LocalDateTime entryTime,
    String timeParked,
    Double lat,
    Double lng
) {
}
