package io.github.pinkchampagne17.channelserver.entity;

import io.github.pinkchampagne17.channelserver.exception.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse extends RuntimeException {

    private String traceId;
    private HttpStatus httpStatus;
    private ErrorCode code;
    private List<String> messages;

    public ErrorResponse(HttpStatus httpStatus, ErrorCode code, List<String> messages) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.messages = messages;
    }

    public ErrorResponse(HttpStatus httpStatus, ErrorCode code, String message) {
        this(httpStatus, code, List.of(message));
    }

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this(httpStatus, ErrorCode.EQUALS_HTTP_STATUS, List.of(message));
    }

    public ErrorResponse(HttpStatus httpStatus, ErrorCode code) {
        this(httpStatus, code, code.toString());
    }

}
