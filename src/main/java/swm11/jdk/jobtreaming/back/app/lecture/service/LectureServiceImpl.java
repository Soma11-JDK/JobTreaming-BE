package swm11.jdk.jobtreaming.back.app.lecture.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.lecture.repository.LectureRepository;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("lectureService")
public class LectureServiceImpl implements LectureService {

    private LectureRepository lectureRepository;

    @Override
    public Optional<Lecture> findById(Long id) {
        return lectureRepository.findById(id);
    }

    @Override
    public List<Lecture> findAllByQuery(String query) {
        return lectureRepository.findAllByTitleContaining(query);
    }

    @Override
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> findMyAvailableList(User user) {
        return lectureRepository.findAllByStudentsContainingAndStartedAtAfter(user, LocalDateTime.now());
    }

    @Override
    public Lecture save(Lecture lecture) {
        // TODO 파일을 스토리지에 업로드 및 파일 이름 or URL set 필요
        return lectureRepository.save(lecture);
    }

    @Override
    public Optional<Lecture> isValidUser(Long lectureId, User user) {
        return lectureRepository.findByIdAndStudentsContaining(lectureId, user);
    }

}
