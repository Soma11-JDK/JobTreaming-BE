package swm11.jdk.jobtreaming.back.app.expert.service;

import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;

import java.util.List;
import java.util.Optional;

public interface ExpertEvaluationService {

    List<ExpertEvaluation> findAll(int pageNum);

    List<ExpertEvaluation> findAllByExpertId(Long expertId, int pageNum);

    Optional<ExpertEvaluation> findById(Long id);

    ExpertEvaluation save(ExpertEvaluation expertEvaluation);

    void delete(Long id);

    boolean isNotDuplicated(ExpertEvaluation expertEvaluation, Long writerId);
}
