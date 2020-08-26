package swm11.jdk.livexpert.back.app.expert.service;

import swm11.jdk.livexpert.back.app.expert.model.ExpertEvaluation;

import java.util.List;
import java.util.Optional;

public interface ExpertEvaluationService {

    List<ExpertEvaluation> findAll();

    List<ExpertEvaluation> findAllByExpertId(Long expertId);

    Optional<ExpertEvaluation> findById(Long id);

    ExpertEvaluation save(ExpertEvaluation expertEvaluation);

    void delete(Long id);
}
