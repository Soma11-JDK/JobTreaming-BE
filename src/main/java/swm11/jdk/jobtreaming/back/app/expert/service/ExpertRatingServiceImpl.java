package swm11.jdk.jobtreaming.back.app.expert.service;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluationDetail;
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
    private Gson gson;

    @Override
    public Optional<ExpertRating> findById(Long id) {
        return expertRatingRepository.findById(id);
    }

    @Override
    public void updateRatings() {
        List<ExpertEvaluation> evaluationList = expertEvaluationRepository.findAll();
        Map<Expert, List<ExpertEvaluation>> map = evaluationList.stream().collect(groupingBy(ExpertEvaluation::getExpert));

        map.keySet().forEach(e -> {
            ExpertEvaluationDetail detail = new ExpertEvaluationDetail();
            List<ExpertEvaluation> list = map.get(e);
            list.stream().map(v -> gson.fromJson(v.getEvaluation(), ExpertEvaluationDetail.class)).forEach(detail::add);
            int size = list.size();
            e.setExpertRating(ExpertRating.builder()
                    .rating1(100 * detail.getIsSelected1() / size)
                    .rating2(100 * detail.getIsSelected2() / size)
                    .rating3(100 * detail.getIsSelected3() / size)
                    .rating4(100 * detail.getIsSelected4() / size)
                    .rating5(100 * detail.getIsSelected5() / size)
                    .rating6(100 * detail.getIsSelected6() / size).build());
            expertRatingRepository.save(e.getExpertRating());
        });

    }

}
