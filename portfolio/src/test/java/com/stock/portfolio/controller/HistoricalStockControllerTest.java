package com.stock.portfolio.controller;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.service.HistoricalStockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class HistoricalStockControllerTest {

    @InjectMocks
    private HistoricalStockController historicalStockController;

    @Mock
    private HistoricalStockService historicalStockService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(historicalStockController).build();
    }

    @Test
    void shouldReturnHistoricalDataWhenPresent() throws Exception {
        // Mock data
        List<HistoricalStock> mockData = Arrays.asList(new HistoricalStock(1L, "AAPL", 150.0, LocalDate.now()));

        // Service mock setup
        given(historicalStockService.getHistoricalData()).willReturn(mockData);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/stocks/getAllHistoricalStocks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].symbol").value("AAPL"))
                .andExpect(jsonPath("$[0].price").value(150.0));
              
    }

    @Test
    void shouldReturnNotFoundWhenNoHistoricalData() throws Exception {
        // Mock empty list
        given(historicalStockService.getHistoricalData()).willReturn(Collections.emptyList());

        // Perform GET request and validate 404 status
        mockMvc.perform(get("/api/stocks/getAllHistoricalStocks"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnServerErrorWhenExceptionOccurs() throws Exception {
        // Mock exception
        given(historicalStockService.getHistoricalData()).willThrow(new RuntimeException("Server error"));

        // Perform GET request and validate 500 status
        mockMvc.perform(get("/api/stocks/getAllHistoricalStocks"))
                .andExpect(status().isInternalServerError());
    }
}
