package swm11.jdk.jobtreaming.back.app.lecture.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LectureResponse {

    private String name;
    private Long expertId;
    private boolean isExpert;

}
