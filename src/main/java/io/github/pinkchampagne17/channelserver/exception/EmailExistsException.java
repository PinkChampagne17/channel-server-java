package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class EmailExistsException extends ErrorResponse {

    public EmailExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.EMAIL_EXISTS);
    }

}
