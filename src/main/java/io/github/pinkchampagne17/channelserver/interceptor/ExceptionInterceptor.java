package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(ParameterInvalidException.class)
    public ResponseEntity<ErrorResponse> ParameterInvalidExceptionHandler(ParameterInvalidException e) {
        var errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getAllErrorMessages()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> ExceptionHandler(Exception e) {
//        System.out.println(e.getMessage());
//        return ResponseEntity.internalServerError().build();
//    }

}
