package swm11.jdk.livexpert.back.app.expert.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.livexpert.back.app.expert.model.ExpertEvaluation;
import swm11.jdk.livexpert.back.app.expert.repository.ExpertEvaluationRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("expertEvaluationService")
public class ExpertEvaluationServiceImpl implements ExpertEvaluationService {

    private ExpertEvaluationRepository expertEvaluationRepository;

    @Override
    public List<ExpertEvaluation> findAll() {
        return expertEvaluationRepository.findAll();
    }

    @Override
    public List<ExpertEvaluation> findAllByExpertId(Long expertId) {
        return expertEvaluationRepository.findAllByExpert_Id(expertId);
    }

    @Override
    public Optional<ExpertEvaluation> findById(Long id) {
        return expertEvaluationRepository.findById(id);
    }

    @Override
    public ExpertEvaluation save(ExpertEvaluation expertEvaluation) {
        return expertEvaluationRepository.save(expertEvaluation);
    }

    @Override
    public void delete(Long id) {
        expertEvaluationRepository.deleteById(id);
    }
}
