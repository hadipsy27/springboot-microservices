package com.lab.departementservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Department service REST APIs",
				description = "Department service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Hadi",
						email = "hadi@mail.com",
						url = "https://github.com/hadipsy27"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/hadipsy27/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Department-service doc",
				url = "https://github.com/hadipsy27/license"
		)
)
@SpringBootApplication
//@EnableEurekaClient
public class DepartementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartementServiceApplication.class, args);
	}

}
