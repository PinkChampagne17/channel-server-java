package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Session;
import io.github.pinkchampagne17.channelserver.parameters.SessionCreateParameters;

public interface SessionService {
    Session createSession(SessionCreateParameters parameters);
    Session getSession(String session);
}
