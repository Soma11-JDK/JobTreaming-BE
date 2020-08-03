package swm11.jdk.livexpert.back.app.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class AbstractBoard extends Common implements Serializable {

    @Column(nullable = false, length = 100)
    private String title;                                           // 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;                                        // 내용

}
