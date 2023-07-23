package com.tatamotors.inventorymgmt.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tatamotors.inventorymgmt.dto.StockDto;
import com.tatamotors.inventorymgmt.service.ManageStockService;

@RestController
@RequestMapping("/stock")
public class StockAPIController {

	@Autowired
	ManageStockService service;
	
	@GetMapping(value = "/{stockName}/available",produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<StockDto> getStocks(@PathVariable("stockName") String stockName){
		System.out.println("Invioked Stock api service code : " + this.hashCode());
		return service.getStocks(stockName);
	}
	
	@GetMapping(value = "/transport", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getTransportInfo() throws JsonProcessingException {
		Map<String, Object> dataMap = null;
		ObjectMapper objectMapper = null;
		String jsonResponse = null;
		dataMap = new HashMap<String,Object>();
		dataMap.put("transportType", service.getTransportType());
		dataMap.put("slaDays", service.getSlaDays());
		objectMapper = new ObjectMapper();
		jsonResponse = objectMapper.writeValueAsString(dataMap);
		return  jsonResponse;
	}
}
