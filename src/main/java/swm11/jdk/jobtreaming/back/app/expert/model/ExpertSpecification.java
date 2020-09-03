package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.enums.gender.Specification;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "expert_specification")
@Getter
@DynamicInsert
@DynamicUpdate
public class ExpertSpecification extends Common implements Serializable {

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                                  // 전문가

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Specification specification;                                    // 스펙 사항

    @Column(nullable = false, length = 20, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startedAt;                                                 // 시작 날짜

    @Column(nullable = false, length = 20, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endedAt;                                                   // 종료 날짜

    @Column(nullable = false, length = 100)
    private String contents;                                               // 내용

    @Setter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCertified;                                            // 인증 여부

    @Setter
    @Column
    private String fileURL;                                                 // 파일 경로

    @Transient
    private MultipartFile uploadFile;                                       // 첨부된 파일

}
