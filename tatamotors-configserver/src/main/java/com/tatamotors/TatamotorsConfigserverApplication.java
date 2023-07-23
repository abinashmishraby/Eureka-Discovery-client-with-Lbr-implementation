package com.tatamotors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TatamotorsConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TatamotorsConfigserverApplication.class, args);
	}

}
