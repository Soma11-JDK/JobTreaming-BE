package swm11.jdk.jobtreaming.back.enums.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum Category implements EnumMapperType {

    BUSINESS("경영.회계.사무"),
    SALES("영업판매"),
    HEALTH("보건.의료"),
    FOOD("음식서비스"),
    MACHINE("기계"),
    CHEMISTRY("화학"),
    ELECTRONIC("전가.전자"),
    IT("정보통신");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}