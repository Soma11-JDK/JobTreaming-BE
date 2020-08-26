package swm11.jdk.jobtreaming.back.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum UserRole implements EnumMapperType {

    ROLE_USER("사용자"),
    ROLE_ADMIN("관리자");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}