package swm11.jdk.jobtreaming.back.config.json;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
