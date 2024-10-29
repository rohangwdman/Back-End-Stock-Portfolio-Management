package com.stock.portfolio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stock.portfolio.service.RTStockPriceService;

@RestController
@RequestMapping("/api/")
//@CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*")
public class RTStockPriceController {

	@Autowired
	private RTStockPriceService rtStockPriceService;


	// Get Real Time Price of Stock
	@GetMapping("rtstock/price")
	public ResponseEntity<Double> getStockPrice(@RequestParam String symbol) {
      try {
		Double price = rtStockPriceService.getRealTimePrice(symbol);
		return ResponseEntity.ok(price);
	  } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();                     
     }
	}

}

