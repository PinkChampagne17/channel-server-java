package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UsernameExistsException extends ErrorResponse {

    public UsernameExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_EXISTS);
    }

}
