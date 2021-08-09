package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.exception.SessionExpiredOrNotExistsException;
import io.github.pinkchampagne17.channelserver.repository.SessionRepository;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        var authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new ParameterInvalidException("Header Authorization is needed.");
        }
        if (!authorization.startsWith("basic ") && authorization.length() <= 6) {
            throw new ParameterInvalidException("The format of authorization is invalid");
        }

        var sessionStr = authorization.split("basic ")[1];
        var session = sessionRepository.getSession(sessionStr);
        if (session == null) {
            throw new SessionExpiredOrNotExistsException();
        }

        var currentUser = this.userService.getUserByGid(session.getGid());

        request.setAttribute("user", currentUser);

        return true;
    }
}
