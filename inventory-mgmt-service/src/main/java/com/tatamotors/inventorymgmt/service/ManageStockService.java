package com.tatamotors.inventorymgmt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatamotors.inventorymgmt.dto.StockDto;
import com.tatamotors.inventorymgmt.repositories.StockRepository;

@Service
@ConfigurationProperties(prefix = "stock")
public class ManageStockService {

	private String transportType;

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public void setSlaDays(int slaDays) {
		this.slaDays = slaDays;
	}

	private int slaDays;
	
	@Autowired
	private StockRepository stockRepository;

	@Transactional(readOnly = true)
	public List<StockDto> getStocks(String stockName) {
		System.out.println("Transport Type");
		return ((Stream<StockDto>) stockRepository.findByStockNameLike("%" + stockName + "%").stream().map(s -> {
			StockDto dto = new StockDto();
			BeanUtils.copyProperties(s, dto);
			return dto;
		})).collect(Collectors.toList());
	}

	public String getTransportType() {
		return transportType;
	}

	public int getSlaDays() {
		return slaDays;
	}
}
