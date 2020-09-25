package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureQuestion;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureQuestionRepository;
import swm11.jdk.jobtreaming.back.constants.PageConstants;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureQuestionService")
public class LectureQuestionServiceImpl implements LectureQuestionService {

    private LectureQuestionRepository lectureQuestionRepository;

    @Override
    public List<LectureQuestion> findAll(int pageNum) {
        return lectureQuestionRepository.findAll(PageRequest.of(pageNum, PageConstants.pageSize)).getContent();
    }

    @Override
    public List<LectureQuestion> findAllByWriterId(Long writerId, int pageNum) {
        return lectureQuestionRepository.findAllByWriter_Id(writerId, PageRequest.of(pageNum, PageConstants.pageSize));
    }

    @Override
    public List<LectureQuestion> findAllByLectureId(Long lectureId, int pageNum) {
        return lectureQuestionRepository.findAllByLecture_Id(lectureId, PageRequest.of(pageNum, PageConstants.pageSize));
    }

    @Override
    public Optional<LectureQuestion> findById(Long id) {
        return lectureQuestionRepository.findById(id);
    }

    @Override
    public LectureQuestion save(LectureQuestion lectureQuestion) {
        return lectureQuestionRepository.save(lectureQuestion);
    }

    @Override
    public void delete(Long id) {
        lectureQuestionRepository.deleteById(id);
    }

}
