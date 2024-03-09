package com.metaway.petshopwebsocketsmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class PetshopwebsocketsmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetshopwebsocketsmongoApplication.class, args);
	}

}
