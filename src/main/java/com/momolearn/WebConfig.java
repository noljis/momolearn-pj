package com.momolearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WebConfig {
	
	@Bean
	MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
    public Docket swaggerApi() {
    	
        return new Docket(DocumentationType.OAS_30)
        		.apiInfo(apiInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.momolearn"))
                .build()
                .useDefaultResponseMessages(false); 
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        		.title("MOMOLEARN")
                .description("MOMOLEARN REST API 문서 작성")
                .version("1.0.0")
                .build();
    }
	    
}
