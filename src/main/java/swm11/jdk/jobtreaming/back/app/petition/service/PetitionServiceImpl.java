package swm11.jdk.jobtreaming.back.app.petition.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.petition.model.Petition;
import swm11.jdk.jobtreaming.back.app.petition.repository.PetitionRepository;
import swm11.jdk.jobtreaming.back.app.user.model.User;
import swm11.jdk.jobtreaming.constants.PageConstants;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("petitionService")
public class PetitionServiceImpl implements PetitionService {

    @NonNull
    private PetitionRepository petitionRepository;

    @Override
    public List<Petition> findAll(Integer pageNum) {
        Page<Petition> petitionPage = petitionRepository.findAll(PageRequest.of(pageNum, PageConstants.pageSize));
        return petitionPage.getContent();
    }

    @Override
    public List<Petition> findTop10ByLikes() {
        return petitionRepository.findTop6ByLikes();
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
