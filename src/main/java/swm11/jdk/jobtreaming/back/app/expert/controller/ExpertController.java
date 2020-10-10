package swm11.jdk.jobtreaming.back.app.expert.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertSpecification;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertService;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertSpecificationService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;
import swm11.jdk.jobtreaming.back.app.user.service.UserService;
import swm11.jdk.jobtreaming.back.enums.user.UserRole;

import java.util.List;

@Api(description = "전문가 API")
@RestController
@RequestMapping("/expert")
@AllArgsConstructor
public class ExpertController {

    private ExpertService expertService;
    private ExpertSpecificationService expertSpecificationService;
    private UserService userService;

    @ApiOperation("전문가 등록")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody Expert expert, @RequestBody List<ExpertSpecification> specificationList) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 전문가 테이블 추가
        userDetails.setExpert(expert);
        userDetails.setRole(UserRole.ROLE_EXPERT);
        userService.save(userDetails.getUser());

        // 전문가 평점 테이블 추가
        expertService.save(userDetails.getExpert());

        // 전문가 스펙 추가
        specificationList.forEach(s -> s.setExpert(userDetails.getExpert()));
        expertSpecificationService.saveAll(specificationList);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("전체 전문가 목록 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(expertService.findAll());
    }

    @ApiOperation("특정 전문가 상세 조회")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return expertService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("특정 전문가 수정")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody Expert expert, @RequestBody List<ExpertSpecification> specificationList) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getExpert().getId().equals(expert.getId())) {
            expertService.save(expert);
            specificationList.forEach(s -> s.setExpert(userDetails.getExpert()));
            expertSpecificationService.saveAll(specificationList);
            return ResponseEntity.ok().build();

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("특정 전문가 삭제")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody Expert expert) {
        expertService.delete(expert.getId());
        return ResponseEntity.ok().build();
    }

}
