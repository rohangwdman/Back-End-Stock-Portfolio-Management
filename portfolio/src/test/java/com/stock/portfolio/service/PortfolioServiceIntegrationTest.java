package com.stock.portfolio.service;

import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.StockRepo;
import com.stock.portfolio.service.PortfolioService;
import com.stock.portfolio.service.RTStockPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PortfolioServiceIntegrationTest {

 @Autowired
 private PortfolioService portfolioService;

 @Autowired
 private StockRepo stockRepository;

 @MockBean
 private RTStockPriceService stockPriceService;

 @Test
 public void testCalculateTotalPortfolioValue_WithStocks() {
     // Arrange
     Stock stock1 = new Stock("AAPL", 10);
     Stock stock2 = new Stock("GOOGL", 5);
     stockRepository.save(stock1);
     stockRepository.save(stock2);

     when(stockPriceService.getRealTimePrice("AAPL")).thenReturn(150.0);
     when(stockPriceService.getRealTimePrice("GOOGL")).thenReturn(2500.0);

     // Act
     double totalValue = portfolioService.calculateTotalPortfolioValue();

     // Assert
     assertEquals((150.0 * 10) + (2500.0 * 5), totalValue);
 }
}
