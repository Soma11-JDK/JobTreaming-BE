package swm11.jdk.jobtreaming.back.app.lecture.service;

import swm11.jdk.jobtreaming.back.app.lecture.model.LectureAnswer;

import java.util.List;
import java.util.Optional;

public interface LectureAnswerService {

    List<LectureAnswer> findAll(int pageNum);

    List<LectureAnswer> findAllByWriterId(Long writerId, int pageNum);

    List<LectureAnswer> findAllByQuestionId(Long questionId, int pageNum);

    Optional<LectureAnswer> findById(Long id);

    LectureAnswer save(LectureAnswer lectureAnswer);

    void delete(Long id);

}
