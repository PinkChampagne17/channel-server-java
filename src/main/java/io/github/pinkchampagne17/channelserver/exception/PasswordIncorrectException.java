package io.github.pinkchampagne17.channelserver.exception;

import org.springframework.http.HttpStatus;

public class PasswordIncorrectException extends HttpException {

    public PasswordIncorrectException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.PASSWORD_INCORRECT);
    }

}
