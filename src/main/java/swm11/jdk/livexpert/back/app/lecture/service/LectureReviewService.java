package swm11.jdk.livexpert.back.app.lecture.service;

import swm11.jdk.livexpert.back.app.lecture.model.LectureReview;

import java.util.List;
import java.util.Optional;

public interface LectureReviewService {
    
    List<LectureReview> findAll();

    List<LectureReview> findAllByWriterId(Long writerId);

    List<LectureReview> findAllByLectureId(Long lectureId);

    Optional<LectureReview> findById(Long id);

    LectureReview save(LectureReview lectureReview);

    void delete(Long id);
    
}
