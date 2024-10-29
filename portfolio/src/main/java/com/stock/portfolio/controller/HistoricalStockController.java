package com.stock.portfolio.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.service.HistoricalStockService;

@RestController
@RequestMapping("/api/")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*")
public class HistoricalStockController {
	
	@Autowired
	private HistoricalStockService historicalStockService;
	
	// Endpoint to get historical stock data by symbol
		@GetMapping("stocks/getAllHistoricalStocks")
		public ResponseEntity<List<HistoricalStock>> getHistoricalData() {
			try {
				List<HistoricalStock> historicalData = historicalStockService.getHistoricalData();
				if (historicalData.isEmpty()) {
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(historicalData);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
	
}