package com.caycho.estapar.controller.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record WebhookEventRequest(
    String licensePlate,
    Double lat,
    Double lng,
    String eventType
) {
}
