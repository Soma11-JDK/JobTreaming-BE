package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureReview;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureReviewRepository;
import swm11.jdk.jobtreaming.back.constants.PageConstants;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureReviewService")
public class LectureReviewServiceImpl implements LectureReviewService {

    private LectureReviewRepository lectureReviewRepository;

    @Override
    public List<LectureReview> findAll(int pageNum) {
        return lectureReviewRepository.findAll(PageRequest.of(pageNum, PageConstants.pageSize)).getContent();
    }

    @Override
    public List<LectureReview> findAllByWriterId(Long writerId, int pageNum) {
        return lectureReviewRepository.findAllByWriter_Id(writerId, PageRequest.of(pageNum, PageConstants.pageSize));
    }

    @Override
    public List<LectureReview> findAllByLectureId(Long lectureId, int pageNum) {
        return lectureReviewRepository.findAllByLecture_Id(lectureId, PageRequest.of(pageNum, PageConstants.pageSize));
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

    @Override
    public boolean isNotDuplicated(LectureReview lectureReview, Long writerId) {
        return !lectureReviewRepository.existsByLecture_IdAndWriter_Id(lectureReview.getLecture().getId(), writerId);
    }
}
