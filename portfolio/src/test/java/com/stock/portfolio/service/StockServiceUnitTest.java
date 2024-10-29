package com.stock.portfolio.service;


import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.StockRepo;
import com.stock.portfolio.service.RTStockPriceService;
import com.stock.portfolio.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceUnitTest {

 @InjectMocks
 private StockService stockService;

 @Mock
 private StockRepo stockRepo;

 @Mock
 private RTStockPriceService stockPriceService;

 @Test
 public void testSaveStock_Success() {
     // Arrange
     Stock stock = new Stock("AAPL", 10);
    
     // Act
     String result = stockService.saveStock(stock);

     // Assert
     assertEquals("AAPL", result);
     verify(stockRepo, times(1)).save(stock);
     
 }

 @Test
 public void testGetAllStocks_Success() {
     // Arrange
     Stock stock1 = new Stock("AAPL", 10);
     Stock stock2 = new Stock("GOOGL", 15);
     when(stockRepo.findAll()).thenReturn(Arrays.asList(stock1, stock2));

     // Act
     List<Stock> stocks = stockService.getAllStocks();

     // Assert
     assertEquals(2, stocks.size());
     assertEquals("AAPL", stocks.get(0).getSymbol());
     verify(stockRepo, times(1)).findAll();
 }

 @Test
 public void testDeleteStockById() {
     // Act
     stockService.deleteStockById(1L);

     // Assert
     verify(stockRepo, times(1)).deleteById(1L);
 }

 @Test
 public void testFindStockById_Success() {
     // Arrange
     Stock stock = new Stock("AAPL", 10);
     when(stockRepo.findById(1L)).thenReturn(Optional.of(stock));

     // Act
     Stock foundStock = stockService.findStockById(1L);

     // Assert
     assertEquals("AAPL", foundStock.getSymbol());
 }

 @Test
 public void testUpdateStock_Success() {
     // Arrange
     Stock existingStock = new Stock("AAPL", 10);
     Stock updatedDetails = new Stock("AAPL", 20);
     when(stockRepo.findById(1L)).thenReturn(Optional.of(existingStock));
     when(stockRepo.save(existingStock)).thenReturn(existingStock);

     // Act
     Stock updatedStock = stockService.updateStock(1L, updatedDetails);

     // Assert
     assertEquals(20, updatedStock.getQuantity());
     verify(stockRepo, times(1)).save(existingStock);
 }
}
