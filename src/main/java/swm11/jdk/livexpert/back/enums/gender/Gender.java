package swm11.jdk.livexpert.back.enums.gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.livexpert.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum Gender implements EnumMapperType {

    MALE("남성"),
    FEMALE("여성");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}