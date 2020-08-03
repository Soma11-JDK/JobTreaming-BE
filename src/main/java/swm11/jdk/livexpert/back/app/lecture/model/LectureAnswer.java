package swm11.jdk.livexpert.back.app.lecture.model;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.livexpert.back.app.common.model.AbstractBoard;
import swm11.jdk.livexpert.back.app.expert.model.Expert;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "lecture_answer")
@Getter
@DynamicInsert
@DynamicUpdate
public class LectureAnswer extends AbstractBoard implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert writer;                                        // 관리자

    @ManyToOne
    @JoinColumn(name = "lecture_question_id", nullable = false, referencedColumnName = "id")
    private LectureQuestion question;

}
