package com.kata.account.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${server.port}")
    int port;

    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return String.format("localhost:%s/api/v1", port);
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kata.account.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    /**
     * Adds metadata to Swagger
     */
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Bank Account API")
                .description("An API for bank")
                .build();
    }

}
