package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureAnswer;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureAnswerRepository;
import swm11.jdk.jobtreaming.back.constants.PageConstants;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureAnswerService")
public class LectureAnswerServiceImpl implements LectureAnswerService {

    private LectureAnswerRepository lectureAnswerRepository;

    @Override
    public List<LectureAnswer> findAll(int pageNum) {
        return lectureAnswerRepository.findAll(PageRequest.of(pageNum, PageConstants.pageSize)).getContent();
    }

    @Override
    public List<LectureAnswer> findAllByWriterId(Long writerId, int pageNum) {
        return lectureAnswerRepository.findAllByWriter_Id(writerId, PageRequest.of(pageNum, PageConstants.pageSize));
    }

    @Override
    public List<LectureAnswer> findAllByQuestionId(Long questionId, int pageNum) {
        return lectureAnswerRepository.findAllByQuestion_Id(questionId, PageRequest.of(pageNum, PageConstants.pageSize));
    }

    @Override
    public Optional<LectureAnswer> findById(Long id) {
        return lectureAnswerRepository.findById(id);
    }

    @Override
    public LectureAnswer save(LectureAnswer lectureAnswer) {
        return lectureAnswerRepository.save(lectureAnswer);
    }

    @Override
    public void delete(Long id) {
        lectureAnswerRepository.deleteById(id);
    }

}
