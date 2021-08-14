package io.github.pinkchampagne17.channelserver.exception;

public enum ErrorCode {
    UNKNOWN_ERROR(1000, "Unknown error, please contact us."),
    PARAMETER_INVALID(1001, "This parameter(s) are invalid."),
    EMAIL_NOT_EXISTS(1002, "This email not exists."),
    USERNAME_NOT_EXISTS(1003, "This username not exists."),
    USERNAME_OR_EMAIL_NOT_EXISTS(1004, "This username or email not exists."),
    EMAIL_EXISTS(1005, "This email is already taken."),
    USERNAME_EXISTS(1006, "This username is already taken."),
    USERNAME_OR_EMAIL_EXISTS(1007, "This username or email is already taken."),
    PASSWORD_INCORRECT(1008, "This password is incorrect."),
    SESSION_EXPIRED_OR_NOT_EXISTS(1009, "This session is expired or not exists."),
    EQUALS_HTTP_STATUS(1010, "The http status code of this response means what us want to tell you.");

    public final int value;
    private final String message;

    ErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
