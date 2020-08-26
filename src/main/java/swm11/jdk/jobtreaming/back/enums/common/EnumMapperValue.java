package swm11.jdk.jobtreaming.back.enums.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnumMapperValue {

    private String code;
    private String title;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        code = enumMapperType.getCode();
        title = enumMapperType.getTitle();
    }

}
