package swm11.jdk.jobtreaming.back.app.lecture.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.AbstractBoard;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "lecture_question")
@Getter
@DynamicInsert
@DynamicUpdate
public class LectureQuestion extends AbstractBoard implements Serializable {

    @ManyToOne
    @JoinColumn(name = "lecture_id", nullable = false, referencedColumnName = "id")
    private Lecture lecture;                                          // 강연

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User writer;                                              // 작성자

    @OneToMany(mappedBy = "question")
    private List<LectureAnswer> answer = new ArrayList<>();           // 답변

}
