package swm11.jdk.jobtreaming.back.app.lecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureQuestion;
import swm11.jdk.jobtreaming.back.app.lecture.service.LectureQuestionService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;

@Api(description = "강연 질문 API")
@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class LectureQuestionController {

    private LectureQuestionService lectureQuestionService;

    @ApiOperation("전체 강연 질문 목록 조회")
    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureQuestion> lectureQuestionList = lectureQuestionService.findAll(pageNum - 1);
        return ResponseEntity.ok(lectureQuestionList);
    }

    @ApiOperation("강연 질문 목록 조회")
    @GetMapping(value = "/list/{lectureId}")
    public ResponseEntity list(@PathVariable("lectureId") Long lectureId, @RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<LectureQuestion> lectureQuestionList = lectureQuestionService.findAllByLectureId(lectureId, pageNum - 1);
        return ResponseEntity.ok(lectureQuestionList);
    }

    @ApiOperation("나의 강연 질문 목록 조회")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/myList")
    public ResponseEntity myList(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<LectureQuestion> lectureQuestionList = lectureQuestionService.findAllByWriterId(userDetails.getId(), pageNum - 1);
        return ResponseEntity.ok(lectureQuestionList);
    }

    @ApiOperation("새로운 강연 질문 추가")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody LectureQuestion lectureQuestion) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        lectureQuestion.setWriter(userDetails.getUser());
        lectureQuestionService.save(lectureQuestion);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("강연 질문 내용 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody LectureQuestion lectureQuestion) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureQuestion.getWriter().getId().equals(userDetails.getId())) {
            lectureQuestionService.save(lectureQuestion);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 질문 내용 삭제")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody LectureQuestion lectureQuestion) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lectureQuestion.getWriter().getId().equals(userDetails.getId())) {
            lectureQuestionService.delete(lectureQuestion.getId());
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
