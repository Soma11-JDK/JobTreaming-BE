package swm11.jdk.jobtreaming.back.app.expert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertSpecification;

import java.util.List;

public interface ExpertSpecificationRepository extends JpaRepository<ExpertSpecification, Long> {

    List<ExpertSpecification> findAllByExpert_Id(Long id);

}
