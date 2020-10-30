package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
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
@Builder
@AllArgsConstructor
public class ExpertRating extends Common implements Serializable {

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating1;                                             // 첫번째 항목 점수

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating2;                                             // 두번째 항목 점수

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating3;                                             // 세번째 항목 점수

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating4;                                             // 네번째 항목 점수

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating5;                                             // 다섯번째 항목 점수

    @Column(nullable = false, columnDefinition = "integer default '0'")
    private final int rating6;                                             // 여섯번째 항목 점수

}
