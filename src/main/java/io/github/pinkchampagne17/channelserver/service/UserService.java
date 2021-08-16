package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.UserCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserQueryParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserUpdateParameters;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User getUser(UserQueryParameters parameters);
    User getUserByGid(Long gid);
    User getUserByHashId(String id);
    User createUser(UserCreateParameters parameters);
    User getCurrentUser(HttpServletRequest request);
    void UpdateUser(UserUpdateParameters parameters);
}
