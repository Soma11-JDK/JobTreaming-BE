package swm11.jdk.jobtreaming.back.app.banner.model;

import lombok.Getter;
import lombok.Setter;
import swm11.jdk.jobtreaming.back.app.common.model.Common;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "banner")
@Getter
public class Banner extends Common implements Serializable {

    @Setter
    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User writer;                                        // 배너 등록자

    @Column(nullable = false, length = 20)
    private LocalDateTime postAt;                               // 게시 일자

    @Setter
    @Column(nullable = false)
    private String imageURL;                                    // 이미지 경로

}
