package io.github.pinkchampagne17.channelserver.entity;

import io.github.pinkchampagne17.channelserver.exception.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {

    private String traceId;
    private HttpStatus httpStatus;
    private ErrorCode code;
    private List<String> messages;

    public ErrorResponse(HttpStatus httpStatus, ErrorCode code, List<String> messages) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.messages = messages;
    }

    public int getHttpStatus() {
        return this.httpStatus.value();
    }

    public int getCode() {
        return this.code.getValue();
    }
}
