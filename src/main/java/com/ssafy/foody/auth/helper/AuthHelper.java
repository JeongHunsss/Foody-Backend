package com.ssafy.foody.auth.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthHelper {
	// 현재 접속자가 관리자 권한을 가졌는지 확인
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication.getAuthorities() == null) {
            return false;
        }

        // 권한 목록(List)을 순회하면서 "ROLE_ADMIN"이 있는지 확인
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}
