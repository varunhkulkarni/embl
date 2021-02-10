package com.embl.embl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Documentation config 
 * @author Varun-Kulkarni
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String TITLE = "REST API";
	private static final String VERSION = "2019.02";
	private static final String DESCRIPTION = "REST API documentation.";
	private static final String SWAGGER_CONTROLLER_PACKAGE = "com.embl.embl.controllers";

	/**
	 * Bean to initialize the swagger configuration 
	 * @return
	 */
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()        
            .apis(RequestHandlerSelectors.basePackage(SWAGGER_CONTROLLER_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false);
    }

	@Bean
	ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title(TITLE).version(VERSION).description(DESCRIPTION);
		return builder.build();
	}
}