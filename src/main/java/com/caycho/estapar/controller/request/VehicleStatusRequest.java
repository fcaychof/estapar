package com.caycho.estapar.controller.request;

import lombok.Builder;

@Builder
public record VehicleStatusRequest(
    String licensePlate
) {
}
