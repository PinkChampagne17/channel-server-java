package io.github.pinkchampagne17.channelserver.exception;

import io.github.pinkchampagne17.channelserver.entity.ErrorResponse;
import org.springframework.http.HttpStatus;

public class PasswordIncorrectException extends ErrorResponse {

    public PasswordIncorrectException() {
        super(HttpStatus.BAD_REQUEST, ErrorCode.PASSWORD_INCORRECT);
    }

}
