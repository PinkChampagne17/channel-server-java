package io.github.pinkchampagne17.channelserver.interceptor;

import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.exception.SessionExpiredOrNotExistsException;
import io.github.pinkchampagne17.channelserver.exception.UnauthorizedException;
import io.github.pinkchampagne17.channelserver.service.SessionService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        var authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new UnauthorizedException();
        }

        var AuthType = "Bearer ";

        if (!authorization.startsWith(AuthType) || authorization.length() <= AuthType.length()) {
            throw new ParameterInvalidException("The format of authorization is invalid");
        }

        var sessionStr = authorization.split(AuthType)[1];
        var session = sessionService.getSession(sessionStr);
        if (session == null) {
            throw new SessionExpiredOrNotExistsException();
        }

        var currentUser = this.userService.getUserByGid(session.getGid());
        if (currentUser == null) {
            throw new SessionExpiredOrNotExistsException();
        }

        request.setAttribute("user", currentUser);
        return true;
    }
}
