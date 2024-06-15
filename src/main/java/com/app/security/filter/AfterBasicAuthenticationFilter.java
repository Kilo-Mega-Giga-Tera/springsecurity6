package com.app.security.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Slf4j
public class AfterBasicAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ObjectUtils.isEmpty(authentication))
            log.info("Authenticated user info: {}, {}", authentication.getName(), authentication.getAuthorities());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
