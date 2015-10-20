package io.pivotal.quotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Microservice to fetch current quotes.
 * 
 * Spring Boot application to provide a service to retrieve current Quote information. 
 * The application registers with a registry service - Eureka.
 * 
 * @author David Ferreira Pinto
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class QuotesApplication {

//	@Bean
//	public AlwaysSampler defaultSampler() {
//		return new AlwaysSampler();
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(QuotesApplication.class, args);
	}
}

