package com.company.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact author = new Contact("Edgar Ruiz", "", "edgarf.ruiz@gmail.com");
		ApiInfo apiInfo = new ApiInfo("Spring RESTful API", "Transactions from OpenBank", "Version 1.0", "urn:tos",
				author, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
		return apiInfo;
	}

}
