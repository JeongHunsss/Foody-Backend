package com.ssafy.foody.common.auth.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.foody.common.auth.jwt.JwtTokenProvider;
import com.ssafy.foody.user.domain.User;
import com.ssafy.foody.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        
        // 인증된 유저 정보 가져오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        
        // 구글의 고유 ID
        String providerId = String.valueOf(oAuth2User.getAttributes().get("sub"));
        
        // 유저 조회
        User dbUser = userMapper.findByProviderId(providerId);
        
        // JWT 토큰 생성 (Access Token)
        String token = jwtTokenProvider.createToken(dbUser.getId());
        
        log.debug("#############################################");
        log.debug("발급된 JWT 토큰: {}", token);
        log.debug("#############################################");

        // 프론트엔드로 리다이렉트 (토큰을 쿼리 파라미터로 전달)
        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/oauth/callback")
                .queryParam("token", token)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
