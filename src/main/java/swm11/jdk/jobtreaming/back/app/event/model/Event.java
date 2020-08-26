package swm11.jdk.jobtreaming.back.app.event.model;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import swm11.jdk.jobtreaming.back.app.common.model.AbstractBoard;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "event")
@Getter
@DynamicInsert
@DynamicUpdate
public class Event extends AbstractBoard implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User user;                                        // 작성자

}
