package com.example.apibasic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정파일 , xml 파일 대신으로
@Configuration
public class SwaggerConfig {

    //xml <bean id=groupedOpenApi class=org.springdoc.core.GroupedOpenApi; />
    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi
                .builder()
                .group("lalala-project")
                .pathsToMatch("/posts/**","/users/**") //문서화 시킬 URL패턴
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return  new OpenAPI()
                .info(
                        new Info()
                                .title("my api~")
                                .description("내 API 명세서")
                                .version("v1.0.0")

                );
    }

}
