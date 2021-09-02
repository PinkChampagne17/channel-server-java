package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
