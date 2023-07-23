package com.tatamotors.dealers;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tatamotors.dealers.dto.StockDto;
import com.tatamotors.dealers.service.StockApiService;

@SpringBootApplication
@EnableDiscoveryClient
public class AutomobileDealerApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		//ApplicationContext context = SpringApplication.run(AutomobileDealerApplication.class, args);
		
		/*
		 * Way to making spring web app to standalone either thorugh the spring config or below fluent builder api changes
		 */
		
		SpringApplication springApplication = new SpringApplicationBuilder(AutomobileDealerApplication.class).web(WebApplicationType.NONE).build();
		ApplicationContext context = springApplication.run(args);
		/*----*/
		/*InventoryMgmtServiceDiscoveryManager inventoryMgmtServiceDiscoveryManager = context
				.getBean(InventoryMgmtServiceDiscoveryManager.class);

		List<ServiceInstance> stockApiInstances = inventoryMgmtServiceDiscoveryManager.getStockApiInstances();
		stockApiInstances.stream().forEach(instance -> {
			System.out.println(instance.getHost() + ": " + instance.getPort());
		});

		AutoMobileProductionServiceDiscoveryManager autoMobileProductionServiceDiscoveryManager = context
				.getBean(AutoMobileProductionServiceDiscoveryManager.class);
		List<ServiceInstance> produServiceInstances = autoMobileProductionServiceDiscoveryManager
				.getStockApiInstances();
		produServiceInstances.stream().forEach(instance -> {
			System.out.println(instance.getHost() + ": " + instance.getPort());
		});*/
		
		StockApiService stockApiService = context.getBean(StockApiService.class);
		List<StockDto> stocks = stockApiService.getStocks("spark plug");
		stocks.stream().forEach(System.out::println);
		
		stocks = stockApiService.getStocks("spark plug");
		stocks.stream().forEach(System.out::println);
		
		stocks = stockApiService.getStocks("spark plug");
		stocks.stream().forEach(System.out::println);
		
	}

}
