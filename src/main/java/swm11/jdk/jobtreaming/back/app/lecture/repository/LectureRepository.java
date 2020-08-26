package swm11.jdk.jobtreaming.back.app.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, Long> {

    List<Lecture> findAllByTitleContaining(String query);

}
