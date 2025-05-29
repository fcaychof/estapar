package com.caycho.estapar.controller.request;

import lombok.Builder;

@Builder
public record SpotStatusRequest(
    double lat,
    double lng) {
}
