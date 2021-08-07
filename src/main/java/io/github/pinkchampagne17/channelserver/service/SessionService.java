package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Session;
import io.github.pinkchampagne17.channelserver.parameters.CreateSessionParameters;

public interface SessionService {
    Session CreateSession(CreateSessionParameters parameters);
}
