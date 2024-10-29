package com.stock.portfolio.service;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.repository.HistoricalStockRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoricalStockServiceUnitTest {

    @Mock
    private HistoricalStockRepo historicalStockRepo;

    @InjectMocks
    private HistoricalStockService historicalStockService;

    @Test
    public void getHistoricalData_ShouldReturnListOfHistoricalStocks() {
        HistoricalStock stock1 = new HistoricalStock("AAPL", 150.0, LocalDate.now());
        HistoricalStock stock2 = new HistoricalStock("GOOGL", 2800.0, LocalDate.now());
        
        when(historicalStockRepo.findAll()).thenReturn(Arrays.asList(stock1, stock2));

        List<HistoricalStock> historicalData = historicalStockService.getHistoricalData();

        assertEquals(2, historicalData.size());
        assertEquals("AAPL", historicalData.get(0).getSymbol());
        assertEquals("GOOGL", historicalData.get(1).getSymbol());
    }
}

