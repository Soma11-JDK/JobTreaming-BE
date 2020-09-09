package swm11.jdk.jobtreaming.back.app.expert.service;

import swm11.jdk.jobtreaming.back.app.expert.model.ExpertRating;

import java.util.Optional;

public interface ExpertRatingService {

    void updateRatings();

    Optional<ExpertRating> findById(Long id);

}
