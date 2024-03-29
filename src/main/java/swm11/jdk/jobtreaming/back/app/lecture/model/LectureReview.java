package swm11.jdk.jobtreaming.back.app.lecture.model;

import lombok.Getter;
import lombok.Setter;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lecture_review")
@Getter
public class LectureReview extends Common implements Serializable {

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User writer;                                                // 작성자

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Lecture lecture;                                            // 강연

    @Column(nullable = false, length = 100)
    private String contents;                                            // 후기 내용

    @Column(nullable = false)
    private Integer rating;                                             // 평점

}
