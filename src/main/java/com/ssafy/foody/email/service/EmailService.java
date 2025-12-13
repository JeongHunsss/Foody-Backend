package com.ssafy.foody.email.service;

public interface EmailService {

	// 인증 코드 발송
	void sendVerificationCode(String email);
	
	// 인증 코드 검증
	boolean verifyCode(String email, String code);

	// 임시 비밀번호 발급
	void sendTemporaryPassword(String email, String tempPassword);
	
	// 이메일 중복 체크
	boolean isEmailDuplicate(String email);
}
