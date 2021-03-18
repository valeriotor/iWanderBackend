package com.valeriotor.iWanderBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.valeriotor.iWanderBackend.datahandler")
public class IWanderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IWanderBackendApplication.class, args);
	}

}
