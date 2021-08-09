package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import io.github.pinkchampagne17.channelserver.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> ExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(ParameterInvalidException.class)
    public ResponseEntity<ErrorResponse> ParameterInvalidExceptionHandler(ParameterInvalidException e) {
        var errorResponse = new ErrorResponse(
                1000,
                e.getAllErrorMessages()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EmailNotExistsException.class)
    public ResponseEntity<ErrorResponse> EmailNotExistsExceptionHandler(EmailNotExistsException e) {
        var errorResponse = new ErrorResponse(
                1001,
                "This email not exists."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UsernameNotExistsException.class)
    public ResponseEntity<ErrorResponse> UsernameNotExistsExceptionHandler(UsernameNotExistsException e) {
        var errorResponse = new ErrorResponse(
                1002,
                "This username not exists."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UsernameOrEmailExistsException.class)
    public ResponseEntity<ErrorResponse> UsernameOrEmailExistsExceptionHandler(UsernameOrEmailExistsException e) {
        var errorResponse = new ErrorResponse(
                1003,
                "This username or email is already taken."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<ErrorResponse> UsernameExistsExceptionHandler(UsernameExistsException e) {
        var errorResponse = new ErrorResponse(
                1003,
                "This username is already taken."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorResponse> EmailExistsExceptionHandler(EmailExistsException e) {
        var errorResponse = new ErrorResponse(
                1004,
                "This email is already taken."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponse> PasswordIncorrectExceptionHandler(PasswordIncorrectException e) {
        var errorResponse = new ErrorResponse(
                1005,
                "This password is incorrect."
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
