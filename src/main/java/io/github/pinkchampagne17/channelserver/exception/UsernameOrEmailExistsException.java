package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class UsernameOrEmailExistsException extends HttpException {

    public UsernameOrEmailExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.USERNAME_OR_EMAIL_EXISTS);
    }

}
