package swm11.jdk.livexpert.back.app.petition.model;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.livexpert.back.app.common.model.Common;
import swm11.jdk.livexpert.back.app.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "petition")
@Getter
@DynamicUpdate
public class Petition extends Common implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User user;                                          // 최초 등록한 사람

    @Column(nullable = false, length = 20)
    private String title;                                       // 청원 제목

    @ManyToMany
    @JoinTable(name = "petition_likes", joinColumns = @JoinColumn(name = "petition_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likes = new HashSet<>();                  // 추가로 청원을 신청한 사람

}
