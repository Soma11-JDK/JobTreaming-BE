package swm11.jdk.jobtreaming.back.app.income.model;

import lombok.Getter;
import lombok.Setter;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.enums.payment.PayStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "income")
@Getter
public class Income extends Common implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                      // 전문가

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Lecture lecture;                                    // 결제한 강연

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PayStatus status;                                   // 결제 상태

    @Column(nullable = false)
    private Integer price;                                      // 금액

}
