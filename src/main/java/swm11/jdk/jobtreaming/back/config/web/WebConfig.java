package swm11.jdk.jobtreaming.back.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import swm11.jdk.jobtreaming.back.config.security.JwtInterceptor;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/", "classpath:/public/", "classpath:/",
            "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .maxAge(3000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns(Arrays.asList("/expert/register", "/expert/list", "/expert/register", "/expert/delete"))
                .addPathPatterns(Arrays.asList("/evaluation/listAll", "/evaluation/add", "/evaluation/modify", "/evaluation/delete"))
                .addPathPatterns(Arrays.asList("/answer/listAll", "/answer/myList", "/answer/add", "/answer/modify", "/answer/delete"))
                .addPathPatterns(Arrays.asList("/question/listAll", "/question/myList", "/question/add", "/question/modify", "/question/delete"))
                .addPathPatterns(Arrays.asList("/review/listAll", "/review/myList", "/review/add", "/review/modify", "/review/delete"))
                .addPathPatterns(Arrays.asList("/petition/add", "/petition/modify", "/petition/delete"))
                .addPathPatterns(Arrays.asList("/user/modify", "/user/delete"));
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}
