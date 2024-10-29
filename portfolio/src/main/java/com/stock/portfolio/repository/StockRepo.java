package com.stock.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.portfolio.domain.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock,Long> {

}
