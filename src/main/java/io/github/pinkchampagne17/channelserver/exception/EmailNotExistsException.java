package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class EmailNotExistsException extends HttpException {

    public EmailNotExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.EMAIL_NOT_EXISTS);
    }

}
