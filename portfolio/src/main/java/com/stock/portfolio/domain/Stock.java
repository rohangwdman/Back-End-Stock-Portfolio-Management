package com.stock.portfolio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "stock")
public class Stock {
	
	public Stock() {}
	public Stock(String symbol, int quantity) {
		this.symbol = symbol;
		this.quantity = quantity;
	}
	public Stock(String symbol, Double currentPrice) {
		this.symbol = symbol;
		this.currentPrice = currentPrice;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String symbol;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private Double currentPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}


}
