package swm11.jdk.jobtreaming.back.app.lecture.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.lecture.model.Lecture;
import swm11.jdk.jobtreaming.back.app.lecture.service.LectureService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Api(description = "강연 API")
@RestController
@RequestMapping("/lecture")
@AllArgsConstructor
public class LectureController {

    private LectureService lectureService;

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

    @ApiOperation("새로운 강연 추가")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody Lecture lecture) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        lecture.setExpert(userDetails.getExpert());
        return ResponseEntity.ok(lectureService.save(lecture));
    }

    @ApiOperation("강연 내용 수정")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody Lecture lecture) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (lecture.getExpert().getId().equals(userDetails.getId())) {
            return ResponseEntity.ok(lectureService.save(lecture));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}