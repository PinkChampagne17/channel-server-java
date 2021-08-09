package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.parameters.GetUsersParameters;

public interface UserService {
    User getUser(GetUsersParameters parameters);
    User getUserByGid(Long gid);
    User getUserByHashId(String id);
    User createUser(CreateUserParameters parameters) throws Exception;
}
