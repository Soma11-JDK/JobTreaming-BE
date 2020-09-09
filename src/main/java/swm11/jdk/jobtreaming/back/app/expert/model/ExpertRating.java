package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.Common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "expert_rating")
@Getter
@DynamicInsert
@DynamicUpdate
public class ExpertRating extends Common implements Serializable {

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating1;                                             // 첫번째 항목 점수

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating2;                                             // 두번째 항목 점수

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating3;                                             // 세번째 항목 점수

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating4;                                             // 네번째 항목 점수

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating5;                                             // 다섯번째 항목 점수

    @Setter
    @Column(nullable = false, columnDefinition = "double precision default '0'")
    private double rating6;                                             // 여섯번째 항목 점수

}
