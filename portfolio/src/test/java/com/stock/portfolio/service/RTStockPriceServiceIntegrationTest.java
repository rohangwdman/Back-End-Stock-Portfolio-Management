package com.stock.portfolio.service;

import com.stock.portfolio.service.RTStockPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RTStockPriceServiceIntegrationTest {

    @Autowired
    private RTStockPriceService rtStockPriceService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetRealTimePrice() {
        // Arrange
        String symbol = "AAPL";
        Double mockPrice = 150.00;
        
        // Mock the behavior of RestTemplate for a specific URL
        String url = "https://api.example.com/stock/" + symbol + "/price";
        when(restTemplate.getForObject(url, Double.class)).thenReturn(mockPrice);

        // Act
        Double price = rtStockPriceService.getRealTimePrice(symbol);

        // Assert
        assertNotNull(price, "The price should not be null");
        assertEquals(mockPrice, price, "The price should match the mocked value");
    }
}
