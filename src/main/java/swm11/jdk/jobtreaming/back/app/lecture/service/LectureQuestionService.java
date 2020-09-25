package swm11.jdk.jobtreaming.back.app.lecture.service;

import swm11.jdk.jobtreaming.back.app.lecture.model.LectureQuestion;

import java.util.List;
import java.util.Optional;

public interface LectureQuestionService {

    List<LectureQuestion> findAll(int pageNum);

    List<LectureQuestion> findAllByWriterId(Long writerId, int pageNum);

    List<LectureQuestion> findAllByLectureId(Long lectureId, int pageNum);

    Optional<LectureQuestion> findById(Long id);

    LectureQuestion save(LectureQuestion lectureQuestion);

    void delete(Long id);

}
