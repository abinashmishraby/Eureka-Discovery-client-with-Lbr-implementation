package com.tatamotors.lbrdealer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatamotors.lbrdealer.sto.StockDto;


@Service
public class StockService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	
	
	public List<StockDto> getStocks(String stockName){
		ResponseEntity<List<StockDto>> responseEntity = null;
		String url = null;
		Map<String, Object> uriVariables = null;
		List<StockDto> stockDtos = null;

		uriVariables = new HashMap<>();
		uriVariables.put("stockName", stockName);
		url = UriComponentsBuilder.fromUriString("http://INVENTORY-MGMT-SERVICE/stock/{stockName}/available")
		.uriVariables(uriVariables).build().toUriString();
		System.out.println(url);
		
		responseEntity = restTemplate.exchange(url, HttpMethod.GET,null,new ParameterizedTypeReference<List<StockDto>>() {
		});
		if(responseEntity.getStatusCode()==HttpStatus.OK) {
			stockDtos = responseEntity.getBody();
		}
		return stockDtos;
	}
	
	
	

}
