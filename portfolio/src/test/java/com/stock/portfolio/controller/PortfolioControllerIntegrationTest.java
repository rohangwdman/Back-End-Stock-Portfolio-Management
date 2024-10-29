package com.stock.portfolio.controller;


import com.stock.portfolio.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PortfolioControllerIntegrationTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private PortfolioService portfolioService;

 @Test
 public void testGetTotalPortfolioValue_Success() throws Exception {
     double mockTotalValue = 5000.0;
     when(portfolioService.calculateTotalPortfolioValue()).thenReturn(mockTotalValue);

     mockMvc.perform(get("/api/portfolio/totalValue")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(content().string("5000.0"));
 }

 @Test
 public void testGetTotalPortfolioValue_Failure() throws Exception {
     when(portfolioService.calculateTotalPortfolioValue()).thenThrow(new RuntimeException("Service error"));

     mockMvc.perform(get("/api/portfolio/totalValue")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isInternalServerError())
             .andExpect(content().string("Failed to Fetch stock: Service error"));
 }
}
