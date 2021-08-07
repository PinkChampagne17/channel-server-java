package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> ExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(ParameterInvalidException.class)
    public ResponseEntity<ErrorResponse> ParameterInvalidExceptionHandler(ParameterInvalidException e) {
        var errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getAllErrorMessages()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
