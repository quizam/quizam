package com.quizam.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quizam.controller"))
                .paths(postPaths())
                .build()
                .apiInfo(metaData());
    }

    private Predicate<String> postPaths() {
        return or(regex("/quizam/.*"), regex("/quizam.*"));
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Quizam",
                "Spring Boot REST API for question set",
                "1.0",
                "Terms of service",
                new Contact("Quizam", "", "quizam@quizam.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
