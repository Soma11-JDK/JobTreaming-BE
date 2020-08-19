package swm11.jdk.livexpert.back.app.expert.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.livexpert.back.app.expert.model.Expert;
import swm11.jdk.livexpert.back.app.expert.repository.ExpertRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("expertService")
public class ExpertServiceImpl implements ExpertService {

    private ExpertRepository expertRepository;

    @Override
    public List<Expert> findAll() {
        return expertRepository.findAll();
    }

    @Override
    public Optional<Expert> findById(Long id) {
        return expertRepository.findById(id);
    }

    @Override
    public Expert save(Expert expert) {
        return expertRepository.save(expert);
    }

    @Override
    public void delete(Long id) {
        expertRepository.deleteById(id);
    }
}
