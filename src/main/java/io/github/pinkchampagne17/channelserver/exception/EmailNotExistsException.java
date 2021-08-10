package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EmailNotExistsException extends ErrorResponse {

    public EmailNotExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.EMAIL_NOT_EXISTS);
    }

}
