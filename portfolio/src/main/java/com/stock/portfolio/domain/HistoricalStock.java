package com.stock.portfolio.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "historicalstock")
public class HistoricalStock {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private String symbol;
	@Column(nullable = false)
    private double price;
	@Column(nullable = false)
    private LocalDate date;

    
    public HistoricalStock() {}

    public HistoricalStock(String symbol, double price, LocalDate date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }
    
    public HistoricalStock(Long id, String symbol, double price, LocalDate date) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.price = price;
		this.date = date;
	}

  
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
