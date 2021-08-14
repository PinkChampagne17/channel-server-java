package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class UsernameExistsException extends HttpException {

    public UsernameExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_EXISTS);
    }

}
