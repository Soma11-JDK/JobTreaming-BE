package swm11.jdk.jobtreaming.back.app.lecture.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureQuestion;

import java.util.List;

@Repository
public interface LectureQuestionRepository extends JpaRepository<LectureQuestion, Long> {

    List<LectureQuestion> findAllByLecture_Id(Long lectureId, PageRequest pageRequest);

    List<LectureQuestion> findAllByWriter_Id(Long writerId, PageRequest pageRequest);

}
