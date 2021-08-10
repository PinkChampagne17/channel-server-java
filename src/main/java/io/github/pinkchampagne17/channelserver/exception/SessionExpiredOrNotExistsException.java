package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class SessionExpiredOrNotExistsException extends ErrorResponse {

    public SessionExpiredOrNotExistsException() {
        super(HttpStatus.FORBIDDEN, ErrorCode.SESSION_EXPIRED_OR_NOT_EXISTS);
    }

}
