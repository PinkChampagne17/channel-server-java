package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "Session that we gave you is needed.");
    }

}
