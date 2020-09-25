package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureRepository;

import java.util.List;

@AllArgsConstructor
@Service("lectureService")
public class LectureServiceImpl implements LectureService {

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
        // TODO 파일을 스토리지에 업로드 및 파일 이름 or URL set 필요
        return lectureRepository.save(lecture);
    }
}
