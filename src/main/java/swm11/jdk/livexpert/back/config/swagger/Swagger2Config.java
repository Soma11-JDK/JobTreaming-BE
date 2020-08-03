package swm11.jdk.livexpert.back.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // SwaggerUI를 리소스에 등록
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .select()
                // RestAPI를 찾을 Package 지정
                .apis(RequestHandlerSelectors.basePackage("swm11.jdk.livexpert"))
                // 특정 메소드에 대해서만 적용을 하고 싶다면 @ApiOperation 어노테이션으로 처리 가능
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
                // FIXME required securitySchemes Later
                //.securitySchemes(Collections.singletonList(createSecurityScheme()));

    }

    private ApiInfo createApiInfo(){
        return new ApiInfoBuilder()
                .title("LivExpert Backend")
                .version("1.0")
                .description("LivExpert Backend for Software Maestro11 Project")
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    /*
    private SecurityScheme createSecurityScheme(){
        return new ApiKey(WebConstants.X_ACCESS_TOKEN, WebConstants.X_ACCESS_TOKEN, "header");
    }
    */

}
