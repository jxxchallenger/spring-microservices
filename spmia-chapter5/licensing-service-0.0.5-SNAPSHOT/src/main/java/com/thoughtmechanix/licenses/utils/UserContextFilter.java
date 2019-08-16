package com.thoughtmechanix.licenses.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserContextFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserContextFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        UserContextHolder.getContext().setCorrelationId(request.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(request.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(request.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrgId(request.getHeader(UserContext.ORG_ID));
        
        LOGGER.info("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        filterChain.doFilter(request, response);
    }

}
