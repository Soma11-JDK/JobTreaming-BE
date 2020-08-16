package swm11.jdk.livexpert.back.app.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.livexpert.back.app.user.model.User;
import swm11.jdk.livexpert.back.app.user.service.UserService;

import javax.annotation.Resource;

@Api(description = "사용자 API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @ApiOperation("회원 가입")
    @PostMapping(value = "/signUp")
    public ResponseEntity add(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @ApiOperation("이메일을 통한 사용자 정보 조회")
    @GetMapping(value = "/findAccount")
    public ResponseEntity findAccount(@RequestParam(value = "email") String email) {
        return userService.findByEmail(email)
                .map(u -> ResponseEntity.ok(u.getEmail()))
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("전체 사용자 목록 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(userService.findAll());
    }

    @ApiOperation("특정 사용자 상세 조회")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @ApiOperation("특정 사용자 수정")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/modify")
    public ResponseEntity modify(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @ApiOperation("특정 사용자 삭제")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
