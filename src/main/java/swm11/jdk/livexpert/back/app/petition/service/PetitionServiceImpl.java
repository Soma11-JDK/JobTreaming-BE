package swm11.jdk.livexpert.back.app.petition.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.livexpert.back.app.petition.model.Petition;
import swm11.jdk.livexpert.back.app.petition.repository.PetitionRepository;
import swm11.jdk.livexpert.back.app.user.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("petitionService")
public class PetitionServiceImpl implements PetitionService {

    private PetitionRepository petitionRepository;

    @Override
    public List<Petition> findAll() {
        return petitionRepository.findAll();
    }

    @Override
    public Optional<Petition> findById(Long id) {
        return petitionRepository.findById(id);
    }

    @Override
    public Petition save(Petition petition) {
        return petitionRepository.save(petition);
    }

    @Override
    public Petition like(Petition petition, User user) {
        // 해당 사용자가 청원에 대해 좋아요를 누르지 않은 경우, 사용자를 좋아요 목록에 추가
        if (!petition.getLikes().contains(user)) {
            petition.getLikes().add(user);
        } else {
            // 해당 사용자가 청원에 대해 좋아요를 누른 경우, 사용자를 좋아요 목록에서 삭제
            petition.getLikes().remove(user);
        }
        return petitionRepository.save(petition);
    }

    @Override
    public void delete(Long id) {
        petitionRepository.deleteById(id);
    }

}
