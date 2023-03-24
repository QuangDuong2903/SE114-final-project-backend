package com.quangduong.SE114backend.exception;

import com.quangduong.SE114backend.payload.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage(), request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage());
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR", request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
