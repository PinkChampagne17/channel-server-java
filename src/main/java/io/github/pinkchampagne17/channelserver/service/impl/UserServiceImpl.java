package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.EmailExistsException;
import io.github.pinkchampagne17.channelserver.exception.UsernameExistsException;
import io.github.pinkchampagne17.channelserver.exception.UsernameOrEmailExistsException;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.parameters.GetUsersParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserUpdateParameters;
import io.github.pinkchampagne17.channelserver.repository.UserRepository;
import io.github.pinkchampagne17.channelserver.service.UserService;
import io.github.pinkchampagne17.channelserver.util.HashId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public User getUserByGid(Long gid) {
        var parameters = new GetUsersParameters() {{
            setGid(gid);
        }};
        return getUser(parameters);
    }

    @Override
    public User getUserByHashId(String hashId) {
        var parameters = new GetUsersParameters() {{
            setHashId(hashId);
        }};
        return getUser(parameters);
    }

    public User getUserByUsername(String username) {
        var parameters = new GetUsersParameters() {{
            setUsername(username);
        }};
        return getUser(parameters);
    }

    public User getUserByEmail(String email) {
        var parameters = new GetUsersParameters() {{
            setEmail(email);
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


    public User createUser(CreateUserParameters parameters)
            throws UsernameOrEmailExistsException, UsernameExistsException, EmailExistsException {

        var u = this.getUserByUsername(parameters.getUsername());
        if (u != null) {
            throw new UsernameExistsException();
        }

        u = this.getUserByEmail(parameters.getEmail());
        if (u != null) {
            throw new EmailExistsException();
        }

        var txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            this.userRepository.createGidAndUsername(parameters);

            var gid = parameters.getGid();
            var hashId = HashId.encodeOne(gid);
            parameters.setHashId(hashId);

            this.userRepository.createUserByGid(parameters);
            var user = this.userRepository.getUserById(gid);
            transactionManager.commit(txStatus);

            return user;

        } catch (Exception e) {
            transactionManager.rollback(txStatus);

            if (e instanceof DuplicateKeyException) {
                throw new UsernameOrEmailExistsException();
            }

            throw e;
        }
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        return (User)request.getAttribute("user");
    }

    @Override
    public void UpdateUser(UserUpdateParameters parameters) {
        this.userRepository.updateUser(parameters);
    }

}
