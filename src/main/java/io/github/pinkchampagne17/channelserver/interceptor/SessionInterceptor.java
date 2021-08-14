package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.exception.SessionExpiredOrNotExistsException;
import io.github.pinkchampagne17.channelserver.exception.UnauthorizedException;
import io.github.pinkchampagne17.channelserver.repository.SessionRepository;
import io.github.pinkchampagne17.channelserver.service.SessionService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        var authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new UnauthorizedException();
        }

        if (!authorization.startsWith("basic ") || authorization.length() <= "basic ".length()) {
            throw new ParameterInvalidException("The format of authorization is invalid");
        }

        var sessionStr = authorization.split("basic ")[1];
        var session = sessionService.getSession(sessionStr);
        if (session == null) {
            throw new SessionExpiredOrNotExistsException();
        }

        var currentUser = this.userService.getUserByGid(session.getGid());

        request.setAttribute("user", currentUser);

        return true;
    }
}
