package swm11.jdk.jobtreaming.back.app.expert.service;

import swm11.jdk.jobtreaming.back.app.expert.model.ExpertSpecification;

import java.util.List;
import java.util.Optional;

public interface ExpertSpecificationService {

    List<ExpertSpecification> findAll();

    Optional<ExpertSpecification> findById(Long id);

    List<ExpertSpecification> findAllByExpertId(Long expertId);

    ExpertSpecification certificate(ExpertSpecification expertSpecification);

    void certificateAllByExpertId(Long expertId);

    ExpertSpecification save(ExpertSpecification expertSpecification);

    void saveAll(List<ExpertSpecification> expertSpecificationList);

    void delete(ExpertSpecification expertSpecification);

    void deleteAllByExpertId(Long expertId);
}
