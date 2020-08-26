package swm11.jdk.jobtreaming.back.app.expert.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.expert.model.ExpertSpecification;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertSpecificationService;

import javax.annotation.Resource;

@Api(description = "전문가 스펙 API")
@RestController
@RequestMapping("/spec")
public class ExpertSpecificationController {

    @Resource(name = "expertSpecificationService")
    private ExpertSpecificationService expertSpecificationService;

    @ApiOperation("전문가 스펙 추가")
    @PostMapping(value = "/add")
    public ResponseEntity register(@RequestBody ExpertSpecification expertSpecification) {
        return ResponseEntity.ok(expertSpecificationService.save(expertSpecification));
    }

    @ApiOperation("전체 전문가 스펙 목록 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity list() {
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody ExpertSpecification expertSpecification) {
        return ResponseEntity.ok(expertSpecificationService.save(expertSpecification));
    }

    @ApiOperation("특정 전문가 스펙 삭제")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        expertSpecificationService.delete(id);
        return ResponseEntity.ok().build();
    }

}
