package com.tatamotors.inventorymgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatamotors.inventorymgmt.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	List<Stock> findByStockNameLike(String stockName);

}
