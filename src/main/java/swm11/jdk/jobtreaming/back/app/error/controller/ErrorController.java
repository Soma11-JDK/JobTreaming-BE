package swm11.jdk.jobtreaming.back.app.error.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "에러 처리 API")
@RestController
@RequestMapping("/error")
@AllArgsConstructor
public class ErrorController {

    @GetMapping(value = "/unauthorized")
    public ResponseEntity unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping(value = "/forbidden")
    public ResponseEntity forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
