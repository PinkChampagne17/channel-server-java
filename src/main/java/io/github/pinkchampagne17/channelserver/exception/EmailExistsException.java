package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class EmailExistsException extends HttpException {

    public EmailExistsException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.EMAIL_EXISTS);
    }

}
