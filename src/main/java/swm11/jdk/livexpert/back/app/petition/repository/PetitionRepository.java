package swm11.jdk.livexpert.back.app.petition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm11.jdk.livexpert.back.app.petition.model.Petition;

public interface PetitionRepository extends JpaRepository <Petition, Long> {

}
