package com.stock.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.portfolio.domain.Stock;
import com.stock.portfolio.service.StockService;

@RestController
@RequestMapping("/api/")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
	private StockService stockService;
	

	@PostMapping("/addStock")
	public ResponseEntity<String> addStock(@RequestBody Stock stock) {
		try {
			String symbol = stockService.saveStock(stock);
			return ResponseEntity.ok(symbol + " Stock Added Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to add stock: " + e.getMessage());
		}
	}

	// GET end point to retrieve all stocks
	@GetMapping("stocks/getAllStocks")
	public ResponseEntity<List<Stock>> getAllStocks() {
		try {
			List<Stock> stockList = stockService.getAllStocks();
			if (stockList.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(stockList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// DELETE end point to delete a stock by its ID
	@DeleteMapping("/stock/{stockId}")
	public ResponseEntity<Void> deleteStockById(@PathVariable Long stockId) {
		try {
			stockService.deleteStockById(stockId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// Return an internal server error status with no body content
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Endpoint to get stock by ID
	@GetMapping("/stock/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
		try {
			Stock stock = stockService.findStockById(id);
			if (stock != null) {
				return ResponseEntity.ok(stock);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Endpoint to update a stock by ID
	@PutMapping("/stock/{id}")
	public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
		try {
			Stock updatedStock = stockService.updateStock(id, stockDetails);

			if (updatedStock != null) {
				return ResponseEntity.ok(updatedStock);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
