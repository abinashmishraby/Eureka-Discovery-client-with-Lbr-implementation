package com.tatamotors.dealers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatamotors.dealers.discovery.manager.InventoryMgmtServiceDiscoveryManager;
import com.tatamotors.dealers.dto.StockDto;

import jakarta.annotation.PostConstruct;

@Service
public class StockApiService {

	private final String STOCK_API_BASE_URI = "/stock/{stockName}/available";

	private int lastUsedInstance;

	@Autowired
	private InventoryMgmtServiceDiscoveryManager inventoryMgmtServiceDiscoveryManager;

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void init() {
		lastUsedInstance = 0;
	}

	public List<StockDto> getStocks(String stockName) {
		String uri = null;
		
		ServiceInstance serviceInstance = null;
		Map<String, Object> pathVariables = null;
		List<StockDto> stockDtos = null;

		pathVariables = new HashMap<>();
		pathVariables.put("stockName", stockName);

		serviceInstance = fetchInstanceAndReturn();
		
		uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + STOCK_API_BASE_URI;
		
		System.out.println(uri);
		String url = UriComponentsBuilder.fromUriString(uri).uriVariables(pathVariables).build().toString();
		System.out.println(url);

		ResponseEntity<List<StockDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<StockDto>>() {
				});

		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			stockDtos = responseEntity.getBody();
		}

		return stockDtos;
	}

	// round robin algo to get instance from multiple instances of a single MS
	private ServiceInstance fetchInstanceAndReturn() {

		List<ServiceInstance> availableInstances = null;

		availableInstances = inventoryMgmtServiceDiscoveryManager.getStockApiInstances();

		if (lastUsedInstance < availableInstances.size() - 1) {
			lastUsedInstance++;
		} else {
			lastUsedInstance = 0;
		}
		return availableInstances.get(lastUsedInstance);
	}

}
