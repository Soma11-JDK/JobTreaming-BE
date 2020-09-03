package swm11.jdk.jobtreaming.back.app.petition.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.petition.model.Petition;
import swm11.jdk.jobtreaming.back.app.petition.service.PetitionService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

import java.util.List;

@Api(description = "강연 청원 API")
@RestController
@RequestMapping("/petition")
@AllArgsConstructor
public class PetitionController {

    private PetitionService petitionService;

    @ApiOperation("전체 강연 청원 목록 조회")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestParam(defaultValue = "1", required = false) Integer pageNum) {
        List<Petition> petitionList = petitionService.findAll(pageNum - 1);
        return ResponseEntity.ok(petitionList);
    }

    @ApiOperation("인기 강연 청원 목록 조회")
    @GetMapping(value = "/hot")
    public ResponseEntity hot() {
        List<Petition> petitionList = petitionService.findTop10ByLikes();
        return ResponseEntity.ok(petitionList);
    }

    @ApiOperation("새로운 강연 청원 추가")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestParam Petition petition) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        petition.setUser(userDetails.getUser());
        petitionService.save(petition);

        return ResponseEntity.ok().build();
    }

    @ApiOperation("강연 청원 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestParam Petition petition) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getId().equals(petition.getUser().getId())) {
            petitionService.save(petition);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("강연 청원 좋아요")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/like")
    public ResponseEntity like(@RequestParam Petition petition) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        petitionService.like(petition, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

}
