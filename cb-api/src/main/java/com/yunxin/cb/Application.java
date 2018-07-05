package com.yunxin.cb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
