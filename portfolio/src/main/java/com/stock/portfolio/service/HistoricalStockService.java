package com.stock.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.repository.HistoricalStockRepo;

@Service
public class HistoricalStockService {
	
	@Autowired
	HistoricalStockRepo historicalStockRepo;
	
	// getAll historical data
	public List<HistoricalStock> getHistoricalData() {
		return historicalStockRepo.findAll();
	}

}
