package com.stock.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.portfolio.domain.HistoricalStock;


@Repository
public interface HistoricalStockRepo extends JpaRepository<HistoricalStock,Long> {

}

