package swm11.jdk.jobtreaming.back.app.lecture.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureAnswer;

import java.util.List;

@Repository
public interface LectureAnswerRepository extends JpaRepository<LectureAnswer, Long> {

    List<LectureAnswer> findAllByQuestion_Id(Long lectureId, PageRequest pageRequest);

    List<LectureAnswer> findAllByWriter_Id(Long writerId, PageRequest pageRequest);

}
