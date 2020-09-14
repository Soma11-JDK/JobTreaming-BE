package swm11.jdk.jobtreaming.back.app.expert.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertEvaluation;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertEvaluationService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;

@Api(description = "전문가 평가 API")
@RestController
@RequestMapping("/evaluation")
@AllArgsConstructor
public class ExpertEvaluationController {

    private ExpertEvaluationService expertEvaluationService;

    @ApiOperation("전체 전문가 평가 목록 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<ExpertEvaluation> evaluationList = expertEvaluationService.findAll(pageNum - 1);
        return ResponseEntity.ok(evaluationList);
    }

    @ApiOperation("특정 전문가 평가 목록 조회")
    @GetMapping(value = "/list/{expertId}")
    public ResponseEntity list(@PathVariable("expertId") Long expertId, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<ExpertEvaluation> evaluationList = expertEvaluationService.findAllByExpertId(expertId, pageNum - 1);
        return ResponseEntity.ok(evaluationList);
    }

    @ApiOperation("전문가 평가 추가")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody ExpertEvaluation expertEvaluation) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (expertEvaluationService.isNotDuplicated(expertEvaluation, userDetails.getId())) {
            expertEvaluation.setWriter(userDetails.getUser());
            expertEvaluationService.save(expertEvaluation);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation("전문가 평가 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody ExpertEvaluation expertEvaluation) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (expertEvaluation.getWriter().getId().equals(userDetails.getId())) {
            expertEvaluationService.save(expertEvaluation);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("전문가 평가 삭제")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody ExpertEvaluation expertEvaluation) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (expertEvaluation.getWriter().getId().equals(userDetails.getId())) {
            expertEvaluationService.delete(expertEvaluation.getId());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
