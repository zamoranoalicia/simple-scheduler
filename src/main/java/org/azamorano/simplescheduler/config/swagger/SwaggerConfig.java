package org.azamorano.simplescheduler.config.swagger;

import lombok.AllArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@AllArgsConstructor
public class SwaggerConfig {
    private final BuildProperties buildProperties;

    @Bean
    public Docket redirectApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("simple-scheduler")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Simple Scheduler Service")
                .description("An API to Handle Students Enrollments")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .version(buildProperties.getVersion())
                .build();
    }
}
