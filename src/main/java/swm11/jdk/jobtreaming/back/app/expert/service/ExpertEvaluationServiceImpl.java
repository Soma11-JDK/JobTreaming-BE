package swm11.jdk.jobtreaming.back.app.expert.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;
import swm11.jdk.jobtreaming.back.app.expert.repository.ExpertEvaluationRepository;
import swm11.jdk.jobtreaming.back.constants.PageConstants;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("expertEvaluationService")
public class ExpertEvaluationServiceImpl implements ExpertEvaluationService {

    private ExpertEvaluationRepository expertEvaluationRepository;

    @Override
    public List<ExpertEvaluation> findAll(int pageNum) {
        return expertEvaluationRepository.findAll(PageRequest.of(pageNum, PageConstants.pageSize)).getContent();
    }

    @Override
    public List<ExpertEvaluation> findAllByExpertId(Long expertId, int pageNum) {
        return expertEvaluationRepository.findAllByExpert_Id(expertId, PageRequest.of(pageNum, PageConstants.pageSize));
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

    @Override
    public boolean isNotDuplicated(ExpertEvaluation expertEvaluation, Long writerId) {
        return !expertEvaluationRepository.existsByExpert_IdAndWriter_Id(expertEvaluation.getExpert().getId(), writerId);
    }

}
