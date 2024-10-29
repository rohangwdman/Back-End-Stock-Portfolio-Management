package com.stock.portfolio.controller;

import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockControllerUnitTest {

 @InjectMocks
 private StockController stockController;

 @Mock
 private StockService stockService;

 @Test
 public void testAddStock_Success() {
     Stock stock = new Stock();
     stock.setSymbol("AAPL");

     when(stockService.saveStock(stock)).thenReturn("AAPL");
     ResponseEntity<String> response = stockController.addStock(stock);

     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals("AAPL Stock Added Successfully", response.getBody());
 }

 @Test
 public void testGetAllStocks_Success() {
     Stock stock1 = new Stock();
     stock1.setSymbol("AAPL");
     Stock stock2 = new Stock();
     stock2.setSymbol("GOOGL");

     List<Stock> stockList = Arrays.asList(stock1, stock2);
     when(stockService.getAllStocks()).thenReturn(stockList);

     ResponseEntity<?> response = stockController.getAllStocks();
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertEquals(stockList, response.getBody());
 }

 @Test
 public void testDeleteStockById_Success() {
     doNothing().when(stockService).deleteStockById(1L);

     ResponseEntity<?> response = stockController.deleteStockById(1L);
     assertEquals(HttpStatus.OK, response.getStatusCode());
 }

 @Test
 public void testGetStockById_NotFound() {
     when(stockService.findStockById(99L)).thenReturn(null);

     ResponseEntity<?> response = stockController.getStockById(99L);
     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
 }
}
