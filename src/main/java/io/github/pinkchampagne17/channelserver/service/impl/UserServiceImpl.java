package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.interceptor.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.repository.UserRepository;
import io.github.pinkchampagne17.channelserver.service.UserService;
import io.github.pinkchampagne17.channelserver.util.HashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return this.userRepository.getUserById(id);
    }

    public User createUser(CreateUserParameters parameters) throws Exception {
        this.userRepository.createGidAndUsername(parameters);
        var gid = parameters.getGid();
        var hashId = HashId.encodeOne(gid);
        parameters.setHashId(hashId);

        this.userRepository.createUserByGid(parameters);
        var user = this.userRepository.getUserById(gid);

        return user;
    }

}
