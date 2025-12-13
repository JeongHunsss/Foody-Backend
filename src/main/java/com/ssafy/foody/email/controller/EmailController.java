package com.ssafy.foody.email.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.foody.email.dto.EmailRequest;
import com.ssafy.foody.email.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;
	
	/**
     * 인증 코드 발송 요청
     * POST /email/send
     * Body: { "email": "test@naver.com" }
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendVerificationCode(request.getEmail());
        return ResponseEntity.ok("인증 코드가 발송되었습니다.");
    }

    /**
     * 인증 코드 검증 요청
     * POST /email/verify
     * Body: { "email": "test@naver.com", "code": "123456" }
     */
    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailRequest request) {
        boolean isVerified = emailService.verifyCode(request.getEmail(), request.getCode());
        
        if (isVerified) {
            return ResponseEntity.ok("이메일 인증 성공!");
        } else {
            return ResponseEntity.status(400).body("인증 코드가 올바르지 않거나 만료되었습니다.");
        }
    }
    
    // 이메일 중복 체크
    // GET /email/check-email?email=test@naver.com
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean exists = emailService.isEmailDuplicate(email);
        return ResponseEntity.ok(exists); // true면 중복, false면 사용 가능
    }
}
