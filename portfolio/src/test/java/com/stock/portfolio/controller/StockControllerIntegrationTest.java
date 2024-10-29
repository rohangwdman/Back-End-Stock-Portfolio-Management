package com.stock.portfolio.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerIntegrationTest {

 @Autowired
 private MockMvc mockMvc;


 @Test
 public void testAddStock() throws Exception {
     String stockJson = "{\"symbol\":\"AAPL\", \"price\":150}";

     mockMvc.perform(post("/api/addStock")
             .contentType(MediaType.APPLICATION_JSON)
             .content(stockJson))
             .andExpect(status().isOk())
             .andExpect(content().string("AAPL Stock Added Successfully"));
 }

 @Test
 public void testGetAllStocks() throws Exception {
     mockMvc.perform(get("/api/stocks/getAllStocks"))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON));
 }

 @Test
 public void testGetStockById_NotFound() throws Exception {
     mockMvc.perform(get("/api/stock/999"))
             .andExpect(status().isNotFound());
 }
}

