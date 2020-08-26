package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureReview;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureRepository;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureReviewRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureReviewService")
public class LectureReviewServiceImpl implements LectureReviewService {

    private LectureReviewRepository lectureReviewRepository;

    @Override
    public List<LectureReview> findAll() {
        return lectureReviewRepository.findAll();
    }

    @Override
    public List<LectureReview> findAllByWriterId(Long writerId) {
        return lectureReviewRepository.findAllByWriter_Id(writerId);
    }

    @Override
    public List<LectureReview> findAllByLectureId(Long lectureId) {
        return lectureReviewRepository.findAllByLecture_Id(lectureId);
    }

    @Override
    public Optional<LectureReview> findById(Long id) {
        return lectureReviewRepository.findById(id);
    }

    @Override
    public LectureReview save(LectureReview lectureReview) {
        return lectureReviewRepository.save(lectureReview);
    }

    @Override
    public void delete(Long id) {
        lectureReviewRepository.deleteById(id);
    }
}
