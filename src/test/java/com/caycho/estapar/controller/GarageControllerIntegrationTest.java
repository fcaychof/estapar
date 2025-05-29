package com.caycho.estapar.controller;

import com.caycho.estapar.controller.request.SpotStatusRequest;
import com.caycho.estapar.controller.request.VehicleStatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GarageControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testGetPlateStatus() throws Exception {
    VehicleStatusRequest request = new VehicleStatusRequest("ABC1234");
    mockMvc.perform(post("/api/plate-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  void testGetSpotStatus() throws Exception {
    SpotStatusRequest request = new SpotStatusRequest(1.0, 2.0);
    mockMvc.perform(post("/api/spot-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  void testGetRevenue() throws Exception {
    mockMvc.perform(get("/api/revenue")
            .param("date", "2024-06-01")
            .param("sector", "A"))
        .andExpect(status().isOk());
  }
}