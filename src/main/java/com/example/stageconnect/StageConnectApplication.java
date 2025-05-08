package com.example.stageconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		"com.example.stageconnect"
})
@SpringBootApplication
public class StageConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StageConnectApplication.class, args);
	}

}
