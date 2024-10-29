package com.stock.portfolio.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RTStockPriceServiceUnitTest {

    @InjectMocks
    private RTStockPriceService rtStockPriceService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${alphavantage.api.url}")
    private String apiUrl;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    @BeforeEach
    public void setUp() {
        // No need to set up RTStockPriceService manually as we use @InjectMocks
    }

    @Test
    public void testGetRealTimePrice_Success() {
        // Arrange
        String symbol = "AAPL";
        String jsonResponse = "{\"Global Quote\": {\"05. price\": \"150.00\"}}";
        JsonNode mockResponse = Mockito.mock(JsonNode.class);
        JsonNode globalQuote = Mockito.mock(JsonNode.class);
        
        when(restTemplate.getForObject(anyString(), Mockito.eq(JsonNode.class))).thenReturn(mockResponse);
        when(mockResponse.has("Global Quote")).thenReturn(true);
        when(mockResponse.get("Global Quote")).thenReturn(globalQuote);
        when(globalQuote.get("05. price")).thenReturn(globalQuote);
        when(globalQuote.asDouble()).thenReturn(150.00);

        // Act
        Double price = rtStockPriceService.getRealTimePrice(symbol);

        // Assert
        assertEquals(150.00, price);
    }

    @Test
    public void testGetRealTimePrice_NoGlobalQuote() {
        // Arrange
        String symbol = "INVALID";
        JsonNode mockResponse = Mockito.mock(JsonNode.class);
        
        when(restTemplate.getForObject(anyString(), Mockito.eq(JsonNode.class))).thenReturn(mockResponse);
        when(mockResponse.has("Global Quote")).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            rtStockPriceService.getRealTimePrice(symbol);
        });
        assertEquals("Unable to fetch stock price for symbol: " + symbol, exception.getMessage());
    }
}
