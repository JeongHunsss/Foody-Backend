package com.ssafy.foody.user.service;

import com.ssafy.foody.user.dto.LoginResponse;
import com.ssafy.foody.user.dto.SignupRequest;

public interface AccountService {
	
	boolean isIdDuplicate(String id); // id 중복 체크
	
	void signup(SignupRequest request); // 일반 회원가입
	
	LoginResponse authenticate(String id, String rawPassword); // 로그인 인증
	
	void sendCodeForFindId(String email); // [아이디 찾기] 이메일 인증 코드 발송
	
	String verifyAndGetId(String email, String code); // [아이디 찾기] 인증 코드 검증 및 아이디 반환
	
	void sendCodeForFindPw(String id, String email); // [비밀번호 찾기] 아이디+이메일 확인 후 인증번호 발송
	
	void resetPassword(String id, String email, String code); // [비밀번호 찾기] 인증번호 검증 -> 임시 비밀번호 변경 및 발송

	
}
