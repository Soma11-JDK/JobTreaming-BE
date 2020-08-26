package swm11.jdk.jobtreaming.back.app.expert.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.expert.model.Expert;
import swm11.jdk.jobtreaming.back.app.expert.service.ExpertService;

import javax.annotation.Resource;

@Api(description = "전문가 API")
@RestController
@RequestMapping("/expert")
public class ExpertController {

    @Resource(name = "expertService")
    private ExpertService expertService;

    @ApiOperation("전문가 등록")
    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody Expert expert) {
        return ResponseEntity.ok(expertService.save(expert));
    }

    @ApiOperation("전체 전문가 목록 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(expertService.findAll());
    }

    @ApiOperation("특정 전문가 상세 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return expertService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("특정 전문가 수정")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody Expert expert) {
        return ResponseEntity.ok(expertService.save(expert));
    }

    @ApiOperation("특정 전문가 삭제")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        expertService.delete(id);
        return ResponseEntity.ok().build();
    }

}
