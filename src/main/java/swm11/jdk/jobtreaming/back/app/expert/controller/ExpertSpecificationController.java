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
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertSpecificationService;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;

@Api(description = "전문가 스펙 API")
@RestController
@RequestMapping("/spec")
@AllArgsConstructor
public class ExpertSpecificationController {

    private ExpertSpecificationService expertSpecificationService;

    @ApiOperation("전체 전문가 스펙 목록 조회")
    @GetMapping(value = "/listAll")
    public ResponseEntity listAll() {
        return ResponseEntity.ok(expertSpecificationService.findAll());
    }

    @ApiOperation("특정 전문가 전체 스펙 상세 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list/{id}")
    public ResponseEntity findAllByExpertId(@PathVariable("id") Long expertId) {
        return ResponseEntity.ok(expertSpecificationService.findAllByExpertId(expertId));
    }

    @ApiOperation("특정 전문가 특정 스펙 상세 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return expertSpecificationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("특정 전문가 스펙 수정")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody ExpertSpecification expertSpecification) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getExpert().getId().equals(expertSpecification.getExpert().getId())) {
            expertSpecificationService.save(expertSpecification);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(expertSpecificationService.save(expertSpecification));
    }

    @ApiOperation("특정 전문가 스펙 삭제")
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody ExpertSpecification expertSpecification) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getExpert().getId().equals(expertSpecification.getExpert().getId())) {
            expertSpecificationService.delete(expertSpecification);
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("특정 전문가 스펙 전체 삭제")
    @PostMapping(value = "/deleteAll")
    public ResponseEntity delete(@RequestBody Expert expert) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getExpert().getId().equals(expert.getId())) {
            expertSpecificationService.deleteAllByExpertId(expert.getId());
            return ResponseEntity.ok().build();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ApiOperation("특정 전문가 스펙 인증")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/certify")
    public ResponseEntity certify(@RequestBody ExpertSpecification expertSpecification) {
        return ResponseEntity.ok(expertSpecificationService.certificate(expertSpecification));
    }

    @ApiOperation("특정 전문가 스펙 전체 인증")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/certifyAll")
    public ResponseEntity certifyAll(@RequestBody Expert expert) {
        expertSpecificationService.certificateAllByExpertId(expert.getId());
        return ResponseEntity.ok().build();
    }

}
