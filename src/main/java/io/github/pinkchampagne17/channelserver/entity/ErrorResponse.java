package io.github.pinkchampagne17.channelserver.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    private int statusCode;
    private List<String> messages;

    public ErrorResponse(int statusCode, List<String> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public ErrorResponse(int statusCode, String message) {
        this(statusCode, List.of(message));
    }

    public ErrorResponse(HttpStatus statusCode, List<String> messages) {
        this(statusCode.value(), messages);
    }

    public ErrorResponse(HttpStatus statusCode, String message) {
        this(statusCode.value(), message);
    }
}
