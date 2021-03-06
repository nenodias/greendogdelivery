package com.boaglio.casadocodigo.greendogdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.boaglio")
public class SpringbootpropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootpropertiesApplication.class, args);
	}

}
