package com.example.AcortarUrl;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AcortarUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcortarUrlApplication.class, args);
	}

	@Bean
	public OpenAPI OpenAPI(){
		Info info=new Info().description("asd").title("hola").version("v1");
		return new OpenAPI().info(info);
	}
}
