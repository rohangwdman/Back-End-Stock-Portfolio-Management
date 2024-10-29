package com.stock.portfolio.controller;


import com.stock.portfolio.service.RTStockPriceService;
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
public class RTStockPriceControllerIntegrationTest {

 @Autowired
 private MockMvc mockMvc;

 @MockBean
 private RTStockPriceService rtStockPriceService;

 @Test
 public void testGetStockPrice_Success() throws Exception {
     String symbol = "AAPL";
     double mockPrice = 150.0;

     when(rtStockPriceService.getRealTimePrice(symbol)).thenReturn(mockPrice);

     mockMvc.perform(get("/api/price")
             .param("symbol", symbol)
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(content().string("150.0"));
 }

 @Test
 public void testGetStockPrice_Failure() throws Exception {
     String symbol = "AAPL";

     when(rtStockPriceService.getRealTimePrice(symbol)).thenThrow(new RuntimeException("Service error"));

     mockMvc.perform(get("/api/price")
             .param("symbol", symbol)
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isInternalServerError())
             .andExpect(content().string("Failed to Fetch stock: Service error"));
 }
}
