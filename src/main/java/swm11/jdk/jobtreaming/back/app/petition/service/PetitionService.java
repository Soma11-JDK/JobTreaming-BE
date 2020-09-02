package swm11.jdk.jobtreaming.back.app.petition.service;

import swm11.jdk.jobtreaming.back.app.petition.model.Petition;
import swm11.jdk.jobtreaming.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

public interface PetitionService {

    List<Petition> findAll(Integer pageNum);

    Optional<Petition> findById(Long id);

    Petition save(Petition petition);

    Petition like(Petition petition, User user);

    void delete(Long id);

}
