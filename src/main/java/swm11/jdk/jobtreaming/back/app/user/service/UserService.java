package swm11.jdk.jobtreaming.back.app.user.service;

import swm11.jdk.jobtreaming.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void delete(Long id);

    Optional<User> findByEmail(String email);

}
