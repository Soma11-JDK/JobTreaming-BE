package swm11.jdk.jobtreaming.back.enums.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum Category implements EnumMapperType {

    IT("정보통신"),
    BUSINESS("사업관리");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}