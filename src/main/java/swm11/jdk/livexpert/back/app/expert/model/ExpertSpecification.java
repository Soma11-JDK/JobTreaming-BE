package swm11.jdk.livexpert.back.app.expert.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import swm11.jdk.livexpert.back.app.common.model.Common;
import swm11.jdk.livexpert.back.enums.gender.Specification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "expert_specification")
@Getter
@DynamicInsert
@DynamicUpdate
public class ExpertSpecification extends Common implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                                  // 전문가

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Specification specification;                                    // 스펙 사항

    @Column(nullable = false, length = 20, updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startedAt;                                                 // 시작 날짜

    @Column(nullable = false, length = 20, updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endedAt;                                                   // 종료 날짜

    @Column(nullable = false, length = 100)
    private String major;                                                   // 내용

    @Setter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCertified;                                            // 인증 여부

    @Setter
    @Column(columnDefinition = "TEXT")
    private String fileURL;                                                 // 파일 경로

}
