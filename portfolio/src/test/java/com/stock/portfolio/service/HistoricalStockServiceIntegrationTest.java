package com.stock.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.repository.HistoricalStockRepo;

@SpringBootTest
@Transactional // Ensure each test runs in a transaction that gets rolled back
public class HistoricalStockServiceIntegrationTest {

    @Autowired
    private HistoricalStockService historicalStockService;

    @Autowired
    private HistoricalStockRepo historicalStockRepo;

    @BeforeEach
    public void setup() {
        historicalStockRepo.deleteAll(); // Clean the database
        HistoricalStock stock1 = new HistoricalStock("AAPL", 150.0, LocalDate.now());
        HistoricalStock stock2 = new HistoricalStock("GOOGL", 2800.0, LocalDate.now());
        historicalStockRepo.save(stock1);
        historicalStockRepo.save(stock2);
    }

    @Test
    public void getHistoricalData_ShouldReturnAllHistoricalStocks() {
        List<HistoricalStock> historicalData = historicalStockService.getHistoricalData();

        assertEquals(2, historicalData.size());
        assertEquals("AAPL", historicalData.get(0).getSymbol());
        assertEquals("GOOGL", historicalData.get(1).getSymbol());
    }
}
