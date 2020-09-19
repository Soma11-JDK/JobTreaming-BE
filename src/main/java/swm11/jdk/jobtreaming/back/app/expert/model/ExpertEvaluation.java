package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "expert_evaluation")
@Getter
@DynamicInsert
@DynamicUpdate
public class ExpertEvaluation extends Common implements Serializable {

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User writer;                                                  // 작성자

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                                // 전문가

    @Column(nullable = false)
    private String evaluation = "";                                       // 평가 내용

}
