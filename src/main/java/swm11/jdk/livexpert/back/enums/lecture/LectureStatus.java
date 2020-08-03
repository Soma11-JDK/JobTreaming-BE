package swm11.jdk.livexpert.back.enums.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import swm11.jdk.livexpert.back.enums.common.EnumMapperType;

@AllArgsConstructor
public enum LectureStatus implements EnumMapperType {

    SCHEDULED("강연 진행예정"),
    PROCEEDING("강연 진행중"),
    COMPLETE("강연 종료");

    @Getter
    private String title;

    @Override
    public String getCode() {
        return name();
    }

}