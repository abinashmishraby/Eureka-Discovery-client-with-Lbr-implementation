package com.tatamotors.lbrdealer;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tatamotors.lbrdealer.service.StockService;
import com.tatamotors.lbrdealer.sto.StockDto;

@SpringBootApplication
public class AutomobileDealerLbrApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AutomobileDealerLbrApplication.class, args);
		StockService stockService = context.getBean(StockService.class);
		for (int i = 0; i < 8; i++) {
			List<StockDto> stocks = stockService.getStocks("break pad");
			stocks.stream().forEach(System.out::println);
		}
	}

}
