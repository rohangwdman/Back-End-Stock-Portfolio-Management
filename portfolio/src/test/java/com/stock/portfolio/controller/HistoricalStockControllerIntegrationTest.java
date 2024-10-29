package com.stock.portfolio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.repository.HistoricalStockRepo;

@SpringBootTest
@AutoConfigureMockMvc
class HistoricalStockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HistoricalStockRepo historicalStockRepository;

    @BeforeEach
    void setUp() {
        historicalStockRepository.deleteAll(); // Clean the database before each test
    }

    @Test
    void shouldReturnHistoricalDataWhenPresent() throws Exception {
        // Given
        HistoricalStock historicalStock = new HistoricalStock(1L, "AAPL", 150.0, LocalDate.now());
        historicalStockRepository.save(historicalStock);

        // When & Then
        mockMvc.perform(get("/api/stocks/getAllHistoricalStocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$[0].price").value(150.0));
                
    }

    @Test
    void shouldReturnNotFoundWhenNoHistoricalData() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/stocks/getAllHistoricalStocks"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnServerErrorWhenExceptionOccurs() throws Exception {
       
    }
}

