package swm11.jdk.jobtreaming.back.app.lecture.service;

import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;

import java.util.List;

public interface LectureService {

    List<Lecture> findAllByQuery(String query);

    List<Lecture> findAll();

    Lecture save(Lecture lecture);
}
