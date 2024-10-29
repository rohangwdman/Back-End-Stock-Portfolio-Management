package com.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.StockRepo;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private RTStockPriceService stockPriceService;

    @Autowired
    private StockRepo stockRepository; 

    public double calculateTotalPortfolioValue() {
    	
    	double totalValue = 0.0;    
        List<Stock> stocks = stockRepository.findAll();  

         // Iterate over each stock, fetch its real-time price, and calculate total value
        for (Stock stock : stocks) {
            Double currentPrice = stockPriceService.getRealTimePrice(stock.getSymbol());
            // Quantity * Current Price
            totalValue += currentPrice * stock.getQuantity(); 
        }

        return totalValue;
    }
}
