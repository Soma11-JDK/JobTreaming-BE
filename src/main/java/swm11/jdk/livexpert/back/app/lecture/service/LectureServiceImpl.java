package swm11.jdk.livexpert.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.livexpert.back.app.lecture.model.Lecture;
import swm11.jdk.livexpert.back.app.lecture.repository.LectureRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureService")
public class LectureServiceImpl implements LectureService{

    private LectureRepository lectureRepository;

    @Override
    public List<Lecture> findAllByQuery(String query) {
        return lectureRepository.findAllByTitleContaining(query);
    }

    @Override
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureRepository.save(lecture);
    }
}
