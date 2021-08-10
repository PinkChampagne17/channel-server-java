package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UsernameNotExistsException extends ErrorResponse {

    public UsernameNotExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_NOT_EXISTS);
    }

}
