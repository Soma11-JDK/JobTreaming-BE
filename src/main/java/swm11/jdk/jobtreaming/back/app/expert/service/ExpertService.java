package swm11.jdk.jobtreaming.back.app.expert.service;

import swm11.jdk.jobtreaming.back.app.expert.model.Expert;

import java.util.List;
import java.util.Optional;

public interface ExpertService {

    List<Expert> findAll();

    Optional<Expert> findById(Long id);

    Expert save(Expert expert);

    void delete(Long id);
}
