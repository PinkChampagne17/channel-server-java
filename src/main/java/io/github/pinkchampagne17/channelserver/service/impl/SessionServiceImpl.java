package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Session;
import io.github.pinkchampagne17.channelserver.exception.EmailNotExistsException;
import io.github.pinkchampagne17.channelserver.exception.PasswordIncorrectException;
import io.github.pinkchampagne17.channelserver.exception.UsernameNotExistsException;
import io.github.pinkchampagne17.channelserver.parameters.SessionCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserQueryParameters;
import io.github.pinkchampagne17.channelserver.repository.SessionRepository;
import io.github.pinkchampagne17.channelserver.service.SessionService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session createSession(SessionCreateParameters parameters) {
        var condition = new UserQueryParameters();

        var isEmail = parameters.getUsernameOrEmail().contains("@");
        if (isEmail) {
            condition.setEmail(parameters.getUsernameOrEmail());
        } else {
            condition.setUsername(parameters.getUsernameOrEmail());
        }

        var user = userService.getUser(condition);
        if (user == null) {
            throw isEmail ? new EmailNotExistsException() : new UsernameNotExistsException();
        }
        if (!parameters.getPassword().equals(user.getPassword())) {
            throw new PasswordIncorrectException();
        }

        var session = new Session() {{
            var randomStr = UUID.randomUUID().toString().replaceAll("-", "");
            var nextWeek = LocalDateTime.now(ZoneOffset.UTC).plusDays(7L);

            setSession(randomStr);
            setExpires(nextWeek);
            setGid(user.getGid());
            setHashId(user.getHashId());
            setClient(parameters.getClient());
            setDevice(parameters.getDevice());
        }};

        sessionRepository.createSession(session);

        return session;
    }

    @Override
    public Session getSession(String session) {
        return sessionRepository.getSession(session);
    }
}
