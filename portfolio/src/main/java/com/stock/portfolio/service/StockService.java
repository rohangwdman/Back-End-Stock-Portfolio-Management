package com.stock.portfolio.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.repository.HistoricalStockRepo;
import com.stock.portfolio.repository.StockRepo;

@Service
public class StockService {

	@Autowired
	StockRepo stockRepo;

	@Autowired
	private RTStockPriceService rtStockPriceService;

	@Autowired
	HistoricalStockRepo historicalStockRepo;

	public String saveStock(Stock stock) {
		try {
	
			// Retrieve the current price for the stock symbol
			Double currentPrice = rtStockPriceService.getRealTimePrice(stock.getSymbol());
			if (currentPrice == null) {
				throw new IllegalArgumentException("Unable to fetch Real time stock price for symbol: " + stock.getSymbol());
			}
			// Set the current price to the stock object
			stock.setCurrentPrice(currentPrice);
			
			// Create and populate a HistoricalStock object
			HistoricalStock historicalStock = new HistoricalStock();
			historicalStock.setDate(LocalDate.now());
			historicalStock.setSymbol(stock.getSymbol());
			historicalStock.setPrice(currentPrice);
			
			// Save the stock and historical data
			historicalStockRepo.save(historicalStock);
			stockRepo.save(stock);
			
			// Return the symbol of the saved stock
			return stock.getSymbol();
		} catch (Exception e) {
			throw new RuntimeException("Failed to save stock: " + e.getMessage());
		}
	}

	public List<Stock> getAllStocks() {
		return stockRepo.findAll();
	}

	public void deleteStockById(Long stockId) {
		stockRepo.deleteById(stockId);
	}

	public Stock findStockById(Long id) {
		return stockRepo.findById(id).orElse(null);
	}

	public Stock updateStock(Long id, Stock stockDetails) {
		return stockRepo.findById(id).map(stock -> {
			stock.setSymbol(stockDetails.getSymbol());
			stock.setQuantity(stockDetails.getQuantity());
			stock.setCurrentPrice(stockDetails.getCurrentPrice());
			return stockRepo.save(stock);
		}).orElse(null);
	}

}
