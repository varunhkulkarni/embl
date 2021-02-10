package com.embl.embl;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.embl.embl.model.Person;
import com.embl.embl.repository.PersonRepository;

/**
 * Entry point to application to spring boot application to launch the
 * application
 * 
 * @author Varun-Kulkarni
 *
 */
@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class EmblApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmblApplication.class, args);
	}

	@PostConstruct
	private void setTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}

	/**
	 * To add the objects in into the h2 database
	 * 
	 * @param repository
	 * @return
	 */
	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {
		return args -> {
			repository.save(new Person("Foo", "Bar", 23, "red"));
			repository.save(new Person("John", "Wick", 35, "black"));
			repository.save(new Person("Harry", "Potter", 15, "white"));
		};
	}

}
