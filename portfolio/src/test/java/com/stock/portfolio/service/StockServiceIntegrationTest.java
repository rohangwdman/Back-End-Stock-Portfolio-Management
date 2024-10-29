package com.stock.portfolio.service;

import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.StockRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

@SpringBootTest // Load the entire application context for integration testing
public class StockServiceIntegrationTest {

    @Autowired
    private StockRepo stockRepo;

    @MockBean
    private RTStockPriceService stockPriceService; // Mocking RTStockPriceService

    @Autowired
    private StockService stockService; // Autowiring StockService

    @Test
    public void testSaveStock_Success() {
        // Arrange
        Stock stock = new Stock("AAPL", 10);
        when(stockPriceService.getRealTimePrice(stock.getSymbol())).thenReturn(150.0);

        // Act
        stockService.saveStock(stock);

        // Assert
        Stock savedStock = stockRepo.findById(stock.getId()).orElse(null);
        assertEquals("AAPL", savedStock.getSymbol());
        assertEquals(150.0, savedStock.getCurrentPrice());
    }

    @Test
    public void testGetAllStocks_Success() {
        // Arrange
        stockRepo.save(new Stock("AAPL", 10));
        stockRepo.save(new Stock("GOOGL", 15));

        // Act
        List<Stock> stocks = stockService.getAllStocks();

        // Assert
        assertEquals(2, stocks.size());
    }
}
