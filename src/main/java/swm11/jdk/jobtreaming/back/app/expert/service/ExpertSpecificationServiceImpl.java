package swm11.jdk.jobtreaming.back.app.expert.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertSpecification;
import swm11.jdk.jobtreaming.back.app.expert.repository.ExpertSpecificationRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("expertSpecificationService")
public class ExpertSpecificationServiceImpl implements ExpertSpecificationService {

    private ExpertSpecificationRepository expertSpecificationRepository;

    @Override
    public List<ExpertSpecification> findAll() {
        return expertSpecificationRepository.findAll();
    }

    @Override
    public Optional<ExpertSpecification> findById(Long id) {
        return expertSpecificationRepository.findById(id);
    }

    @Override
    public List<ExpertSpecification> findAllByExpertId(Long expertId) {
        return expertSpecificationRepository.findAllByExpert_Id(expertId);
    }

    @Override
    public ExpertSpecification certificate(ExpertSpecification expertSpecification) {
        expertSpecification.setIsCertified(true);
        return expertSpecificationRepository.save(expertSpecification);
    }

    @Override
    public void certificateAllByExpertId(Long expertId) {
        expertSpecificationRepository.findAllByExpert_Id(expertId).forEach(this::certificate);
    }

    @Override
    public ExpertSpecification save(ExpertSpecification expertSpecification) {
        return expertSpecificationRepository.save(expertSpecification);
    }

    @Override
    public void delete(Long id) {
        expertSpecificationRepository.deleteById(id);
    }

    @Override
    public void deleteAllByExpertId(Long expertId) {
        expertSpecificationRepository.findAllByExpert_Id(expertId).forEach(e -> expertSpecificationRepository.delete(e));
    }
}
