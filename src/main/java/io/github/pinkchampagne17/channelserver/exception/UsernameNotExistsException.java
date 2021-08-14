package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotExistsException extends HttpException {

    public UsernameNotExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_NOT_EXISTS);
    }

}
