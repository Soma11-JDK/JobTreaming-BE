package swm11.jdk.jobtreaming.back.app.lecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.lecture.model.LectureResponse;
import swm11.jdk.jobtreaming.back.app.lecture.service.LectureService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;
import swm11.jdk.jobtreaming.back.app.user.service.UserService;
import swm11.jdk.jobtreaming.back.exception.LectureNotFoundException;
import swm11.jdk.jobtreaming.back.exception.UserNotFoundException;
import swm11.jdk.jobtreaming.back.utils.LectureUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "강연 API")
@RestController
@RequestMapping("/lecture")
@AllArgsConstructor
public class LectureController {

    private LectureService lectureService;
    private UserService userService;

    @ApiOperation("강연 목록 조회")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll(@RequestParam(value = "query", required = false) String query, @RequestParam(value = "category", required = false) String category) {
        List<Lecture> lectureList;
        if (query != null) {
            lectureList = lectureService.findAllByQuery(query);
        } else {
            lectureList = lectureService.findAll();
        }

        if (category != null) {
            lectureList = lectureList.stream().filter(l -> l.getCategory().getCode().equals(category)).collect(Collectors.toList());
        }

        return ResponseEntity.ok(lectureList);
    }

    @ApiOperation("인기 강연 목록 조회")
    @GetMapping(value = "/hot")
    public ResponseEntity hot(@RequestParam(value = "category", required = false) String category) {
        List<Lecture> lectureList = lectureService.findAll();
        if (category != null) {
            lectureList = lectureList.stream().filter(l -> l.getCategory().getCode().equals(category)).limit(5).collect(Collectors.toList());
        }

        return ResponseEntity.ok(lectureList);
    }

    @ApiOperation("특정 강연 상세 조회")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Lecture lecture = lectureService.findById(id).orElseThrow(LectureNotFoundException::new);
        lecture.setExpertName(userService.findById(lecture.getId()).orElseThrow(() -> new UserNotFoundException("")).getName());
        return ResponseEntity.ok(lecture);
    }

    @ApiOperation("새로운 강연 추가")
    @PreAuthorize("hasRole('ROLE_EXPERT')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Lecture lecture) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        lecture.setExpert(userDetails.getExpert());
        return ResponseEntity.ok(lectureService.save(lecture));
    }

    @ApiOperation("강연 내용 수정")
    @PreAuthorize("hasRole('ROLE_EXPERT')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody Lecture lecture) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lecture.getExpert().getId().equals(userDetails.getId())) {
            return ResponseEntity.ok(lectureService.save(lecture));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 참여")
    @PreAuthorize("hasRole('ROLE_EXPERT') or hasRole('ROLE_USER')")
    @GetMapping(value = "/join")
    public ResponseEntity join(HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        Long lectureId = Long.valueOf(request.getParameter("lectureId"));
        String password = request.getParameter("password");
        Lecture lecture = lectureService.findById(lectureId).orElseThrow(LectureNotFoundException::new);

        if (LectureUtils.enableJoin(password, lectureId)) {
            return ResponseEntity.badRequest().build();
        }

        if (lecture.getExpert().getId().equals(userId)) {
            return ResponseEntity.ok(LectureResponse.builder().name(userDetails.getName()).isExpert(true).build());
        } else {
            boolean isValid = lectureService.isValidUser(lectureId, userDetails.getUser()).isPresent();
            return isValid ? ResponseEntity.ok(LectureResponse.builder().name(userDetails.getName()).isExpert(false).build()) : ResponseEntity.badRequest().build();
        }

    }

    /*
    private ExpertService expertService;

    @ApiOperation("테스트 강연 생성")
    @GetMapping(value = "/test")
    public ResponseEntity test(@RequestParam(value = "title") String title) throws InvalidKeyException, NoSuchAlgorithmException {
        Expert expert = expertService.findById(new Long(1)).get();
        Lecture lecture = new Lecture();
        lecture.setExpert(expert);
        lecture.setTitle(title);
        lecture.setCategory(Category.IT);
        lecture.setTarget("타겟");
        lecture.setStartedAt(LocalDateTime.now());
        lecture.setEndedAt(LocalDateTime.now());
        lecture.setMaxNum(10);
        lecture.setPrice(10000);
        lecture.setTimetable("시간표");
        lecture.setOverview("개요");
        lecture.setContents("내용");
        lecture.setStatus(LectureStatus.COMPLETE);
        lecture.setKeywords("키워드");
        lecture.setAvgRating(0);
        lecture = lectureService.save(lecture);
        lectureService.save(lecture);
        return ResponseEntity.ok().build();
    }
    */

}
