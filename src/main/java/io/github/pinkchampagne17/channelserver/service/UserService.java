package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;

public interface UserService {
    User getUserById(Long id);
    User createUser(CreateUserParameters parameters) throws Exception;
}
