package com.stock.portfolio.controller;


import com.stock.portfolio.service.RTStockPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RTStockPriceControllerUnitTest {

 @InjectMocks
 private RTStockPriceController rtStockPriceController;

 @Mock
 private RTStockPriceService rtStockPriceService;

 @Test
 public void testGetStockPrice_Success() {
     // Arrange
     String symbol = "AAPL";
     double mockPrice = 150.0;
     when(rtStockPriceService.getRealTimePrice(symbol)).thenReturn(mockPrice);

     // Act
     ResponseEntity<?> response = rtStockPriceController.getStockPrice(symbol);

     // Assert
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(mockPrice, response.getBody());
 }

 @Test
 public void testGetStockPrice_Failure() {
     // Arrange
     String symbol = "AAPL";
     when(rtStockPriceService.getRealTimePrice(symbol)).thenThrow(new RuntimeException("Service error"));

     // Act
     ResponseEntity<?> response = rtStockPriceController.getStockPrice(symbol);

     // Assert
     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
     assertEquals("Failed to Fetch stock: Service error", response.getBody());
 }
}
