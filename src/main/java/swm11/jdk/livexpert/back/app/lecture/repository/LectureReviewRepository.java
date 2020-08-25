package swm11.jdk.livexpert.back.app.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swm11.jdk.livexpert.back.app.lecture.model.LectureReview;

import java.util.List;

@Repository
public interface LectureReviewRepository extends JpaRepository <LectureReview, Long> {

    List<LectureReview> findAllByLecture_Id(Long lectureId);

    List<LectureReview> findAllByWriter_Id(Long writerId);

}
