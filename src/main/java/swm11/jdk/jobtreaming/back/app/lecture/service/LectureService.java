package swm11.jdk.jobtreaming.back.app.lecture.service;

import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

public interface LectureService {

    Optional<Lecture> findById(Long id);

    List<Lecture> findAllByQuery(String query);

    List<Lecture> findAll();

    Lecture save(Lecture lecture);

    Optional<Lecture> isValidUser(Long lectureId, User user);
}
