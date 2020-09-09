package swm11.jdk.jobtreaming.back.app.expert.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertRating;
import swm11.jdk.jobtreaming.back.app.expert.repository.ExpertEvaluationRepository;
import swm11.jdk.jobtreaming.back.app.expert.repository.ExpertRatingRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@AllArgsConstructor
@Service("expertRatingService")
public class ExpertRatingServiceImpl implements ExpertRatingService {

    private ExpertRatingRepository expertRatingRepository;
    private ExpertEvaluationRepository expertEvaluationRepository;

    @Override
    public Optional<ExpertRating> findById(Long id) {
        return expertRatingRepository.findById(id);
    }

    @Override
    public void updateRatings() {
        // FIXME 코드 리팩토링 확인 필요
        List<ExpertEvaluation> evaluationList = expertEvaluationRepository.findAll();
        Map<Expert, List<ExpertEvaluation>> map = evaluationList.stream().collect(groupingBy(ExpertEvaluation::getExpert));
        map.keySet().forEach(e -> {
            List<ExpertEvaluation> list = map.get(e);
            long rating = 100 * list.stream().filter(ExpertEvaluation::isSelected1).count() / list.size();
            e.getExpertRating().setRating1(rating);

            rating = 100 * list.stream().filter(ExpertEvaluation::isSelected2).count() / list.size();
            e.getExpertRating().setRating2(rating);

            rating = 100 * list.stream().filter(ExpertEvaluation::isSelected3).count() / list.size();
            e.getExpertRating().setRating3(rating);

            rating = 100 * list.stream().filter(ExpertEvaluation::isSelected4).count() / list.size();
            e.getExpertRating().setRating4(rating);

            rating = 100 * list.stream().filter(ExpertEvaluation::isSelected5).count() / list.size();
            e.getExpertRating().setRating5(rating);

            rating = 100 * list.stream().filter(ExpertEvaluation::isSelected6).count() / list.size();
            e.getExpertRating().setRating6(rating);

            expertRatingRepository.save(e.getExpertRating());
        });

    }

}
