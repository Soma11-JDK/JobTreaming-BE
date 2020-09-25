package swm11.jdk.jobtreaming.back.app.lecture.service;

import swm11.jdk.jobtreaming.back.app.lecture.model.LectureReview;

import java.util.List;
import java.util.Optional;

public interface LectureReviewService {

    List<LectureReview> findAll(int pageNum);

    List<LectureReview> findAllByWriterId(Long writerId, int pageNum);

    List<LectureReview> findAllByLectureId(Long lectureId, int pageNum);

    Optional<LectureReview> findById(Long id);

    LectureReview save(LectureReview lectureReview);

    void delete(Long id);

    boolean isNotDuplicated(LectureReview lectureReview, Long writerId);
}
