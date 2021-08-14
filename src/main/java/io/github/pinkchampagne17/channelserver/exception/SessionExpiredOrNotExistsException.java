package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class SessionExpiredOrNotExistsException extends HttpException {

    public SessionExpiredOrNotExistsException() {
        super(HttpStatus.FORBIDDEN, ErrorCode.SESSION_EXPIRED_OR_NOT_EXISTS);
    }

}
