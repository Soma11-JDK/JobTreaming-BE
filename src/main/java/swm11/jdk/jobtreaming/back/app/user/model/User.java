package swm11.jdk.jobtreaming.back.app.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.enums.gender.Gender;
import swm11.jdk.jobtreaming.back.enums.user.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@DynamicInsert
@DynamicUpdate
public class User extends Common implements Serializable {

    @Column(nullable = false, length = 100)
    private String email;                                       // 사용자 이메일

    @Column(nullable = false)
    private String pw;                                          // 사용자 비밀번호

    @Column(nullable = false, length = 20)
    private String name;                                        // 사용자 이름

    @Column(nullable = false, length = 13)
    private String phone;                                       // 사용자 연락처

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Gender gender;                                      // 성별

    @Column(length = 20)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime birth;                                // 생년월일

    @OneToOne
    @JoinColumn(name = "expert_id", referencedColumnName = "id")
    private Expert expert;                                      // 전문가 정보

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;                                      // 사용자 역할할

    @Setter
    @Column(nullable = false)
    private String imageURL;                                    // 이미지 경로

}
