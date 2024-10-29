package com.stock.portfolio.controller;

import com.stock.portfolio.service.PortfolioService;
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
public class PortfolioControllerUnitTest {

 @InjectMocks
 private PortfolioController portfolioController;

 @Mock
 private PortfolioService portfolioService;

 @Test
 public void testGetTotalPortfolioValue_Success() {
     // Arrange
     double mockTotalValue = 5000.0;
     when(portfolioService.calculateTotalPortfolioValue()).thenReturn(mockTotalValue);

     // Act
     ResponseEntity<?> response = portfolioController.getTotalPortfolioValue();

     // Assert
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(mockTotalValue, response.getBody());
 }

 @Test
 public void testGetTotalPortfolioValue_Failure() {
     // Arrange
     when(portfolioService.calculateTotalPortfolioValue()).thenThrow(new RuntimeException("Service error"));

     // Act
     ResponseEntity<?> response = portfolioController.getTotalPortfolioValue();

     // Assert
     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
     assertEquals("Failed to Fetch stock: Service error", response.getBody());
 }
}
