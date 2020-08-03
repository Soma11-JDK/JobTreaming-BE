package swm11.jdk.livexpert.back.app.notice.model;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.livexpert.back.app.common.model.AbstractBoard;
import swm11.jdk.livexpert.back.app.expert.model.Expert;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "notice")
@Getter
@DynamicInsert
@DynamicUpdate
public class Notice extends AbstractBoard implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private Expert writer;                                        // 작성자

}
