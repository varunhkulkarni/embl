package com.embl.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author Varun-Kulkarni
 *
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class EmblZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmblZuulApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}

}
