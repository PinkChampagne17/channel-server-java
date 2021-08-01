package io.github.pinkchampagne17.channelserver.exception;

public class SocketMessageDataIllegalException extends Exception {

    public SocketMessageDataIllegalException(IllegalArgumentException e) {
        super(e.getMessage(), e.getCause());
    }

}