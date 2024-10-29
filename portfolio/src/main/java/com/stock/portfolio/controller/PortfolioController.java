package com.stock.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stock.portfolio.service.PortfolioService;

@RestController
@RequestMapping("/api/")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*")
public class PortfolioController {


	@Autowired
	private PortfolioService portfolioService;

	
	// Get Total Portfolio Value
	@GetMapping("/portfolio/totalValue")
	public ResponseEntity<Double> getTotalPortfolioValue() {
	    try {
	        double totalValue = portfolioService.calculateTotalPortfolioValue();
	        
	        return ResponseEntity.ok(totalValue);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	
	
}
