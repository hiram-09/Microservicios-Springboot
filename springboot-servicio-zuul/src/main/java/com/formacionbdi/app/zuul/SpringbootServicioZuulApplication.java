package com.formacionbdi.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulApplication.class, args);
	}

}
