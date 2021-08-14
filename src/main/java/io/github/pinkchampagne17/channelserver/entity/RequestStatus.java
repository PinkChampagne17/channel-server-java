package io.github.pinkchampagne17.channelserver.entity;

public enum RequestStatus {

    WAITING, ACCEPTED, REJECTED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
