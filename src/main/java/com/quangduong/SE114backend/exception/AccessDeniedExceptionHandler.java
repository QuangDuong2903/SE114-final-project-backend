package com.quangduong.SE114backend.exception;

import com.google.gson.Gson;
import com.quangduong.SE114backend.payload.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedExceptionHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.error(accessDeniedException.getMessage());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson()
                .toJson(new ErrorResponse(HttpServletResponse.SC_FORBIDDEN, "Access Denied", request.getRequestURI()));
        response.getWriter().print(json);
    }
}
