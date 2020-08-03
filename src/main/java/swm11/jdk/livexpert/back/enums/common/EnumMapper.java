package swm11.jdk.livexpert.back.enums.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swm11.jdk.livexpert.back.enums.lecture.Category;
import swm11.jdk.livexpert.back.enums.lecture.LectureStatus;

import java.util.LinkedHashMap;

@Configuration
public class EnumMapper {

    @Bean
    public EnumMapperFactory enumMapperFactory() {
        EnumMapperFactory enumMapperFactory = new EnumMapperFactory(new LinkedHashMap<>());
        enumMapperFactory.put("Category", Category.class);
        enumMapperFactory.put("LectureStatus", LectureStatus.class);
        return enumMapperFactory;
    }

}
