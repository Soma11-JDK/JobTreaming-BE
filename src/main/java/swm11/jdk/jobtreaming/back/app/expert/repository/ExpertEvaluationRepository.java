package swm11.jdk.jobtreaming.back.app.expert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;

import java.util.List;

public interface ExpertEvaluationRepository extends JpaRepository<ExpertEvaluation, Long> {

    List<ExpertEvaluation> findAllByExpert_Id(Long expertId);

}
