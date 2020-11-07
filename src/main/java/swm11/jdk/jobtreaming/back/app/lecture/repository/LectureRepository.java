package swm11.jdk.jobtreaming.back.app.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, Long> {

    List<Lecture> findAllByTitleContaining(String query);

    Optional<Lecture> findByIdAndStudentsContaining(Long lectureId, User user);

}
