package com.example.movieportfolio.controller.api;

import com.example.movieportfolio.service.MemberService;
import com.example.movieportfolio.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/userLoginAction.reservation")
    public String login(@RequestBody UserVO userVO) {
        UserVO result = memberService.validateMember(userVO.getUserID(), userVO.getUserPassword());

        if (result != null) {
            return "Login successful!";
        } else {
            return "Login failed!";
        }
    }

    @PostMapping("/userFindAction.reservation")
    public String findPassword(@RequestBody Map<String, String> request) {
        try {
            String userID = request.get("userID");
            String userEmail = request.get("userEmail");

            memberService.findAndSendNewPassword(userID, userEmail);

            return "새 비밀번호가 이메일로 발송되었습니다.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 사용자 정보 수정 API 메소드
    @PutMapping("/userEditAction.reservation/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserVO userVO) {
        try {
            // userId와 userVO를 활용하여 사용자 정보 업데이트 로직 수행

            return ResponseEntity.ok("사용자 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사용자 정보 업데이트 중 오류가 발생했습니다.");
        }
    }

}
