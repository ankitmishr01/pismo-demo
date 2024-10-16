package com.pismo.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("Incoming Request: {} {} at {}",
                request.getMethod(), request.getRequestURI(), LocalDateTime.now());
        return true; // Continue to the next interceptor or controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        logger.info("Outgoing Response: {} {} with status {} at {}",
                request.getMethod(), request.getRequestURI(),
                response.getStatus(), LocalDateTime.now());

        if (ex != null) {
            logger.error("Exception occurred: ", ex);
        }
    }
}
