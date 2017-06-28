package com.carviator.configprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigProviderApplication.class, args);
	}
}
