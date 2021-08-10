package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UsernameOrEmailExistsException extends ErrorResponse {

    public UsernameOrEmailExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_OR_EMAIL_EXISTS);
    }

}
