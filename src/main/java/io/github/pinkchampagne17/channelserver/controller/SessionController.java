package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Session;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.SessionCreateParameters;
import io.github.pinkchampagne17.channelserver.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @PostMapping()
    public ResponseEntity<Session> createSession(
            @RequestBody @Valid SessionCreateParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var session = sessionService.createSession(parameters);
        return ResponseEntity.ok(session);
    }

}
