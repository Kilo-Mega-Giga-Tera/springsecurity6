package com.app.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.web.authentication.www.BasicAuthenticationConverter.AUTHENTICATION_SCHEME_BASIC;

@Slf4j
public class BeforeBasicAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader(AUTHORIZATION);

        if (!ObjectUtils.isEmpty(header)) {
            header = header.trim();

            if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
                byte[] decoded;

                try {
                    decoded = Base64.getDecoder().decode(base64Token);
                    String token = new String(decoded, StandardCharsets.UTF_8);

                    if (!token.contains(":")) {
                        log.error("SC_BAD_REQUEST token error");
                        throw new BadCredentialsException("Invalid token");
                    }

                    String email = token.split(":")[0];

                    if (email.toLowerCase().contains("admin")) {
                        log.error("SC_BAD_REQUEST Cannot use this id: {}", email.toLowerCase());
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }

                } catch (IllegalArgumentException e) {
                    log.error("SC_BAD_REQUEST IllegalArgumentException");
                    throw new BadCredentialsException("Invalid token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
