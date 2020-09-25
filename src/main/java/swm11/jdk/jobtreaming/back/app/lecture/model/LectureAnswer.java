package swm11.jdk.jobtreaming.back.app.lecture.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.AbstractBoard;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "lecture_answer")
@Getter
@DynamicInsert
@DynamicUpdate
public class LectureAnswer extends AbstractBoard implements Serializable {

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert writer;                                        // 관리자

    @Setter
    @ManyToOne
    @JoinColumn(name = "lecture_question_id", nullable = false, referencedColumnName = "id")
    private LectureQuestion question;

}
