package com.tennis.matching.common.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {  // JWT 커스텀필터 클래스

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // 토큰의 인증정보를 SecurityContext에 저장
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("doFilter 1) httpServletRequest: " + httpServletRequest);

        String jwt = getJWTfromToken(httpServletRequest);  // 1) resolveToken 메서드로 토큰을 받아오고
        logger.info("doFilter 3) jwt: " + jwt);

        String requestURI = httpServletRequest.getRequestURI();
        logger.info("doFilter 4) requestURI: " + requestURI);

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {  // 2) 받아온 토큰을 유효성 검증을 진행
            Authentication authentication = tokenProvider.getAuthentication(jwt);  // 3) 토근이 정상이면 Authentication(User객체, 토근, 권한정보가 담긴) 객체를 받아와서
            SecurityContextHolder.getContext().setAuthentication(authentication);  // 4) SecurityContextHolder에 set 해준다
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    // Request Header에서 토큰정보를 꺼내기위한 메서드
    private String getJWTfromToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        logger.info("doFilter 2) bearerToken: " + bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

            return bearerToken.substring(7, bearerToken.length());
        }

//        if (StringUtils.hasText(bearerToken)) {
//            logger.info("doFilter 2) bearerToken: " + bearerToken);
//
//            String[] tokenParts = bearerToken.split(" ");
//            if (tokenParts.length == 2 && tokenParts[0].equals("Bearer")) {
//                return tokenParts[1];
//            }
//        }

        return null;
    }
}
