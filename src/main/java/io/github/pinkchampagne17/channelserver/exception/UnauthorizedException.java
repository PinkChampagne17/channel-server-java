package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ErrorResponse {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "Session us gave you is needed");
    }

}
