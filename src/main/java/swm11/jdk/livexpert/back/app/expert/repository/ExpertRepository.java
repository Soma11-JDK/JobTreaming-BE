package swm11.jdk.livexpert.back.app.expert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm11.jdk.livexpert.back.app.expert.model.Expert;

public interface ExpertRepository extends JpaRepository <Expert, Long> {

}
