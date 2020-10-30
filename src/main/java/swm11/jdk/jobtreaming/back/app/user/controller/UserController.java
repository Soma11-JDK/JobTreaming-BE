package swm11.jdk.jobtreaming.back.app.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import swm11.jdk.jobtreaming.back.app.user.model.MyUserDetails;
import swm11.jdk.jobtreaming.back.app.user.model.User;
import swm11.jdk.jobtreaming.back.app.user.service.UserService;
import swm11.jdk.jobtreaming.back.utils.TokenUtils;

@Api(description = "사용자 API")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserDetailsService userDetailsService;

    @ApiOperation("회원 가입")
    @PostMapping(value = "/signUp")
    public ResponseEntity signUp(@RequestBody User user) {
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
    @GetMapping(value = "/list")
    public ResponseEntity list() {
        return ResponseEntity.ok(userService.findAll());
    }

    @ApiOperation("특정 사용자 상세 조회")
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

    @ApiOperation("소셜 로그인")
    @GetMapping(value = "/socialLogin")
    public ResponseEntity socialLogin() {
        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername("test");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return ResponseEntity.ok("테스트 로그인 성공");
    }

    @ApiOperation("테스트 로그인")
    @GetMapping(value = "/testLogin")
    public ResponseEntity testLogin() {
        return ResponseEntity.ok(TokenUtils.generateJwtToken(userService.findByEmail("test@test.test").get()));
    }

}
