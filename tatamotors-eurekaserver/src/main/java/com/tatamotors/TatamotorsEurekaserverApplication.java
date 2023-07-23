package com.tatamotors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TatamotorsEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TatamotorsEurekaserverApplication.class, args);
	}

}
