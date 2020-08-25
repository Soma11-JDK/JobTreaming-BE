package swm11.jdk.livexpert.back.app.petition.service;

import swm11.jdk.livexpert.back.app.petition.model.Petition;
import swm11.jdk.livexpert.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

public interface PetitionService {

    List<Petition> findAll();

    Optional<Petition> findById(Long id);

    Petition save(Petition petition);

    Petition like(Petition petition, User user);

    void delete(Long id);

}
