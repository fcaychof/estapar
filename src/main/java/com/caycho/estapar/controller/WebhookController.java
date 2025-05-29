package com.caycho.estapar.controller;

import com.caycho.estapar.controller.request.WebhookEventRequest;
import com.caycho.estapar.controller.response.HandleWebhookReponse;
import com.caycho.estapar.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

  private final GarageService garageService;

  @PostMapping
  public ResponseEntity<HandleWebhookReponse> handleWebhook(@RequestBody WebhookEventRequest event) {
    garageService.handleEvent(event);
    return ResponseEntity.ok(HandleWebhookReponse.builder().message("Message received").build());
  }
}
