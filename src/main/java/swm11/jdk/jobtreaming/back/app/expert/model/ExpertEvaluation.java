package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;
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

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User writer;                                                  // 작성자

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                                // 전문가

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected1;                                            // 첫번째 항목 선택 여부

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected2;                                            // 두번째 항목 선택 여부

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected3;                                            // 세번째 항목 선택 여부

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected4;                                            // 네번째 항목 선택 여부

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected5;                                            // 다섯번째 항목 선택 여부

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isSelected6;                                            // 여섯번째 항목 선택 여부

}
