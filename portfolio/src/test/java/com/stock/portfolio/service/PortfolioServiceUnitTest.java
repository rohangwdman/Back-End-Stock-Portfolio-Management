package com.stock.portfolio.service;


import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.StockRepo;
import com.stock.portfolio.service.PortfolioService;
import com.stock.portfolio.service.RTStockPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceUnitTest {

 @InjectMocks
 private PortfolioService portfolioService;

 @Mock
 private StockRepo stockRepository;

 @Mock
 private RTStockPriceService stockPriceService;

 @Test
 public void testCalculateTotalPortfolioValue_EmptyPortfolio() {
     // Arrange
     when(stockRepository.findAll()).thenReturn(Collections.emptyList());

     // Act
     double totalValue = portfolioService.calculateTotalPortfolioValue();

     // Assert
     assertEquals(0.0, totalValue);
     verify(stockRepository, times(1)).findAll();
 }

 @Test
 public void testCalculateTotalPortfolioValue_WithStocks() {
     // Arrange
     Stock stock1 = new Stock("AAPL", 10);
     Stock stock2 = new Stock("GOOGL", 5);
     List<Stock> stocks = Arrays.asList(stock1, stock2);
     
     when(stockRepository.findAll()).thenReturn(stocks);
     when(stockPriceService.getRealTimePrice("AAPL")).thenReturn(150.0);
     when(stockPriceService.getRealTimePrice("GOOGL")).thenReturn(2500.0);

     // Act
     double totalValue = portfolioService.calculateTotalPortfolioValue();

     // Assert
     assertEquals((150.0 * 10) + (2500.0 * 5), totalValue);
     verify(stockRepository, times(1)).findAll();
     verify(stockPriceService, times(1)).getRealTimePrice("AAPL");
     verify(stockPriceService, times(1)).getRealTimePrice("GOOGL");
 }
}
