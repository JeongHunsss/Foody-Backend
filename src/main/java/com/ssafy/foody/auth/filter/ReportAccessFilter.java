package com.ssafy.foody.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

/**
 * ROLE_GUEST 권한을 가진 사용자가 /report 경로로 접근 시
 * 분석을 위한 추가 정보 입력이 필요하다는 메시지와 함께 요청을 차단하는 필터.
 * JwtAuthenticationFilter 이후에 실행
 */
public class ReportAccessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 요청 경로가 /report 로 시작하는지 확인
        if (requestURI.startsWith("/report")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 인증된 사용자 정보가 있고, 그 사용자가 ROLE_GUEST 권한을 가지고 있는지 확인
            if (authentication != null && authentication.isAuthenticated() && isGuest(authentication.getAuthorities())) {

                // GUEST가 리포트에 접근하려 하면 커스텀 에러 응답 반환
                
                // 상태 코드는 403 Forbidden
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                // 프론트엔드가 이 JSON을 받아서 메시지를 띄우고 마이페이지로 라우팅
                String jsonResponse = "{"
                        + "\"code\": \"NEED_ADDITIONAL_INFO\","
                        + "\"message\": \"분석을 위해서는 추가 정보가 필요합니다. 마이페이지로 이동하여 정보를 완성해주세요.\","
                        + "\"redirectUrl\": \"/user\"" // 라우팅 경로
                        + "}";
                
                response.getWriter().write(jsonResponse);
                return;
            }
        }

        // 조건에 해당하지 않으면 다음 필터로 진행
        filterChain.doFilter(request, response);
    }

    private boolean isGuest(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) {
            return false;
        }
        // 권한 목록 중에 ROLE_GUEST가 있는지 확인 (DB 저장 방식에 따라 "GUEST" 일수도 있음. 확인 필요)
        return authorities.stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_GUEST"));
    }
}