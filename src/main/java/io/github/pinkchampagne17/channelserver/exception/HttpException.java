package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class HttpException extends RuntimeException {

    private ErrorResponse errorResponse;

    public HttpException(HttpStatus httpStatus, ErrorCode code, List<String> messages) {
        this.errorResponse = new ErrorResponse(httpStatus, code, messages);
    }

    public HttpException(HttpStatus httpStatus, ErrorCode code, String message) {
        this(httpStatus, code, List.of(message));
    }

    public HttpException(HttpStatus httpStatus, String message) {
        this(httpStatus, ErrorCode.EQUALS_HTTP_STATUS, List.of(message));
    }

    public HttpException(HttpStatus httpStatus, ErrorCode code) {
        this(httpStatus, code, code.toString());
    }

    public HttpException(HttpStatus httpStatus) {
        this(httpStatus, ErrorCode.EQUALS_HTTP_STATUS, ErrorCode.EQUALS_HTTP_STATUS.toString());
    }

}
