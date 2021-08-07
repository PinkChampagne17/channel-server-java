package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.parameters.GetUsersParameters;
import io.github.pinkchampagne17.channelserver.repository.UserRepository;
import io.github.pinkchampagne17.channelserver.service.UserService;
import io.github.pinkchampagne17.channelserver.util.HashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByHashId(String hashId) {
        var parameters = new GetUsersParameters() {{
            setHashId(hashId);
        }};
        return getUser(parameters);
    }

    @Override
    public User getUser(GetUsersParameters parameters) {
        parameters.setCount(1L);

        var users = userRepository.getUsers(parameters);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }


    public User createUser(CreateUserParameters parameters) throws DuplicateKeyException {
        this.userRepository.createGidAndUsername(parameters);

        var gid = parameters.getGid();
        var hashId = HashId.encodeOne(gid);
        parameters.setHashId(hashId);

        this.userRepository.createUserByGid(parameters);
        var user = this.userRepository.getUserById(gid);

        return user;
    }

}
