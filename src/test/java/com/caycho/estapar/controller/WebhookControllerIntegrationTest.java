package com.caycho.estapar.controller;

import com.caycho.estapar.service.GarageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebhookControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private GarageService garageService;

  @Test
  void handleWebhook_shouldReturnMessageReceived() throws Exception {
    String requestBody = "{\n"
        + "  \"license_plate\": \"ZUL0001\",\n"
        + "  \"entry_time\": \"2025-01-01T12:00:00.000Z\",\n"
        + "  \"event_type\": \"ENTRY\"\n"
        + "}";
    
    mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/webhook")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Message received"));
  }
}