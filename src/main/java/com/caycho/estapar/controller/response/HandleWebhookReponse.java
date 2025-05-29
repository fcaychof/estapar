package com.caycho.estapar.controller.response;

import lombok.Builder;

@Builder
public record HandleWebhookReponse(
    String message
) {
}
