package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import io.github.pinkchampagne17.channelserver.exception.HttpException;
import io.github.pinkchampagne17.channelserver.exception.ErrorCode;
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
    public ResponseEntity<HttpException> ExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        var response = new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.UNKNOWN_ERROR, "unknown error.");
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> HttpExceptionHandler(HttpException e) {
        var response = e.getErrorResponse();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
