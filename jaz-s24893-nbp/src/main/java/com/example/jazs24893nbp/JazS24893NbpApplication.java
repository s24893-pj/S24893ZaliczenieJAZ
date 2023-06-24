package com.example.jazs24893nbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class JazS24893NbpApplication {

	public static void main(String[] args) {
		SpringApplication.run(JazS24893NbpApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("Kolokwium JAZ")
						.version(appVersion)
						.description(appDesciption)
						.termsOfService("https://pja.edu.pl/")
						.license(new License().name("Strona PJATK").url("https://pja.edu.pl/")));
	}

}
