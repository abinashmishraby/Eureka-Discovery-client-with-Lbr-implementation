package com.tatamotors.inventorymgmt.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_no")
	private int stockNo;
	@Column(name = "stock_nm")
	private String stockName;
	private String description;
	private int quantity;
	@Column(name = "unit_price")
	private double unitPrice;
	
}
