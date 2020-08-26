package swm11.jdk.jobtreaming.back.app.payment.model;

import lombok.Getter;
import lombok.Setter;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.user.model.User;
import swm11.jdk.jobtreaming.back.enums.payment.PayStatus;
import swm11.jdk.jobtreaming.back.enums.payment.PayType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
@Getter
public class Payment extends Common implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User user;                                        // 결제한 사람

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Lecture lecture;                                  // 결제한 강연

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PayStatus status;                                   // 결제 상태

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PayType type;                                       // 결제 종류

    @Column(nullable = false)
    private Integer price;                                      // 금액

}
