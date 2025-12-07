package com.ssafy.foody.user.service;

import com.ssafy.foody.user.dto.LoginResponse;
import com.ssafy.foody.user.dto.SignupRequest;

public interface AccountService {
	
	boolean isIdDuplicate(String id); // id 중복 체크
	void signup(SignupRequest request); // 일반 회원가입
	LoginResponse authenticate(String id, String rawPassword); // 로그인 인증
}
