package swm11.jdk.jobtreaming.back.app.expert.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertRating;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertRatingService;
import swm11.jdk.jobtreaming.back.exception.ExpertNotFoundException;

@Api(description = "전문가 평점 API")
@RestController
@RequestMapping("/rating")
@AllArgsConstructor
public class ExpertRatingController {

    private ExpertRatingService expertRatingService;

    @ApiOperation("특정 전문가 평점 조회")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        ExpertRating expertRating = expertRatingService.findById(id).orElseThrow(ExpertNotFoundException::new);
        return ResponseEntity.ok(expertRating);
    }

    @ApiOperation("특정 전문가 평점 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/update")
    public ResponseEntity update() {
        expertRatingService.updateRatings();
        return ResponseEntity.ok().build();
    }

}
