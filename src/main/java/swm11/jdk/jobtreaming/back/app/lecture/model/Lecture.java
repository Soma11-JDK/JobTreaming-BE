package swm11.jdk.jobtreaming.back.app.lecture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.user.model.User;
import swm11.jdk.jobtreaming.back.enums.lecture.Category;
import swm11.jdk.jobtreaming.back.enums.lecture.LectureStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecture")
@Getter
@DynamicInsert
@DynamicUpdate
@Setter
public class Lecture extends Common implements Serializable {

    @JsonIgnore
    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert expert;                                            // 강연자

    @Setter
    @Transient
    private String expertName;                                          // 강연자 이름

    @Column(nullable = false, length = 100)
    private String title;                                               // 제목

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Category category;                                          // 카테고리

    @Column(nullable = false, length = 100)
    private String target;                                              // 대상

    @Column(length = 20)
    private LocalDateTime startedAt;                                    // 강연 시작 일자

    @Column(length = 20)
    private LocalDateTime endedAt;                                      // 강연 종료 일자

    @Column(nullable = false)
    private Integer maxNum;                                             // 최대 인원

    @Column(nullable = false)
    private Integer price;                                              // 가격

    @Column(nullable = false, length = 100)
    private String timetable;                                           // 타임 테이블

    @Column(nullable = false, length = 100)
    private String overview;                                            // 강의 개요

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;                                            // 강의 소개

    @Setter
    @Column(nullable = false)
    private String fileName = "";                                       // 이미지 경로

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "lecture_student", joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> students = new ArrayList<>();                    // 신청자

    @JsonIgnore
    @OneToMany(mappedBy = "lecture")
    private List<LectureQuestion> questionList = new ArrayList<>();     // 질문 목록

    @Setter
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private LectureStatus status;                                       // 강연 상태

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "lecture_likes", joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likes = new ArrayList<>();                       // 강연 좋아요 누른 사람들

    @Column(nullable = false, length = 100)
    private String keywords;                                            // 키워드

    @Transient
    private List<MultipartFile> uploadFiles;                            // 첨부된 파일

    @Column(columnDefinition = "double precision default '0'")
    private double avgRating;                                           // 평균 평점

}