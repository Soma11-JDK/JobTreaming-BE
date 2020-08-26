package swm11.jdk.jobtreaming.back.app.enums.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swm11.jdk.jobtreaming.back.enums.common.EnumMapperFactory;

@Api(description = "Enum 상수 API")
@RestController
@AllArgsConstructor
public class EnumsController {

    private EnumMapperFactory enumMapperFactory;

    @ApiOperation("카테고리 목록 조회")
    @GetMapping("/category")
    public ResponseEntity category(){
        return ResponseEntity.ok(enumMapperFactory.get("Category"));
    }

    @ApiOperation("강연 상태 목록 조회")
    @GetMapping("/productStatus")
    public ResponseEntity lectureStatus(){
        return ResponseEntity.ok(enumMapperFactory.get("LectureStatus"));
    }

    @ApiOperation("성별 목록 조회")
    @GetMapping("/gender")
    public ResponseEntity gender(){
        return ResponseEntity.ok(enumMapperFactory.get("Gender"));
    }

    @ApiOperation("스펙 목록 조회")
    @GetMapping("/specification")
    public ResponseEntity specification(){
        return ResponseEntity.ok(enumMapperFactory.get("Specification"));
    }

    @ApiOperation("사용자 역할 목록 조회")
    @GetMapping("/userRole")
    public ResponseEntity userRole(){
        return ResponseEntity.ok(enumMapperFactory.get("UserRole"));
    }

}
