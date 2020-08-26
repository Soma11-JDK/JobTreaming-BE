package swm11.jdk.jobtreaming.back.app.expert.model;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "expert")
@Getter
@DynamicInsert
@DynamicUpdate
public class Expert extends Common implements Serializable {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String about;                                             // 소개글

    @OneToMany(mappedBy = "expert")
    private List<Lecture> lectureList = new ArrayList<>();            // 강연 목록

    @OneToMany(mappedBy = "expert")
    private List<Lecture> specificationList = new ArrayList<>();      // 스펙 목록

}
