package swm11.jdk.jobtreaming.back.app.lecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureAnswer;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureQuestion;
import swm11.jdk.jobtreaming.back.app.lecture.service.LectureAnswerService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;

@Api(description = "강연 답변 API")
@RestController
@RequestMapping("/answer")
@AllArgsConstructor
public class LectureAnswerController {

    private LectureAnswerService lectureAnswerService;

    @ApiOperation("전체 강연 답변 목록 조회")
    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureAnswer> lectureAnswerList = lectureAnswerService.findAll(pageNum - 1);
        return ResponseEntity.ok(lectureAnswerList);
    }

    @ApiOperation("강연 답변 목록 조회")
    @GetMapping(value = "/list/{lectureId}")
    public ResponseEntity list(@PathVariable("lectureId") Long questionId, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureAnswer> lectureAnswerList = lectureAnswerService.findAllByQuestionId(questionId, pageNum - 1);
        return ResponseEntity.ok(lectureAnswerList);
    }

    @ApiOperation("나의 강연 답변 목록 조회")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/myList")
    public ResponseEntity myList(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<LectureAnswer> lectureAnswerList = lectureAnswerService.findAllByWriterId(userDetails.getId(), pageNum - 1);
        return ResponseEntity.ok(lectureAnswerList);
    }

    @ApiOperation("새로운 강연 답변 추가")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody LectureQuestion lectureQuestion, @RequestBody LectureAnswer lectureAnswer) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getExpert() != null) {
            lectureAnswer.setQuestion(lectureQuestion);
            lectureAnswer.setWriter(userDetails.getUser().getExpert());
            lectureAnswerService.save(lectureAnswer);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 답변 내용 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody LectureAnswer lectureAnswer) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureAnswer.getWriter().getId().equals(userDetails.getId())) {
            lectureAnswerService.save(lectureAnswer);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 답변 내용 삭제")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody LectureAnswer lectureAnswer) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureAnswer.getWriter().getId().equals(userDetails.getId())) {
            lectureAnswerService.delete(lectureAnswer.getId());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
