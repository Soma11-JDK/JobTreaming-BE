package swm11.jdk.jobtreaming.back.enums.gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum Specification implements EnumMapperType {

    CAREER("경력사항"),
    MAJOR("학력/전공"),
    CERTIFICATION("자격증");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}