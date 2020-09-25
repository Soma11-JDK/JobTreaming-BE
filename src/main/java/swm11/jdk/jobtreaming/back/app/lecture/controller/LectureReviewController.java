package swm11.jdk.jobtreaming.back.app.lecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureReview;
import swm11.jdk.jobtreaming.back.app.lecture.service.LectureReviewService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;

@Api(description = "강연 리뷰 API")
@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class LectureReviewController {

    private LectureReviewService lectureReviewService;

    @ApiOperation("전체 강연 리뷰 목록 조회")
    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureReview> lectureReviewList = lectureReviewService.findAll(pageNum - 1);
        return ResponseEntity.ok(lectureReviewList);
    }

    @ApiOperation("강연 리뷰 목록 조회")
    @GetMapping(value = "/list/{lectureId}")
    public ResponseEntity list(@PathVariable("lectureId") Long lectureId, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureReview> lectureReviewList = lectureReviewService.findAllByLectureId(lectureId, pageNum - 1);
        return ResponseEntity.ok(lectureReviewList);
    }

    @ApiOperation("나의 강연 리뷰 목록 조회")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/myList")
    public ResponseEntity myList(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<LectureReview> lectureReviewList = lectureReviewService.findAllByWriterId(userDetails.getId(), pageNum - 1);
        return ResponseEntity.ok(lectureReviewList);
    }

    @ApiOperation("새로운 강연 리뷰 추가")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody LectureReview lectureReview) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureReviewService.isNotDuplicated(lectureReview, userDetails.getId())) {
            lectureReview.setWriter(userDetails.getUser());
            lectureReviewService.save(lectureReview);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @ApiOperation("강연 리뷰 내용 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody LectureReview lectureReview) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureReview.getWriter().getId().equals(userDetails.getId())) {
            lectureReviewService.save(lectureReview);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 리뷰 내용 삭제")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody LectureReview lectureReview) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureReview.getWriter().getId().equals(userDetails.getId())) {
            lectureReviewService.delete(lectureReview.getId());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
