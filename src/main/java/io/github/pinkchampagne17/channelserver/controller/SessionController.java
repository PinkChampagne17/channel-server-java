package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.dto.SessionDTO;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateSessionParameters;
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
    public ResponseEntity<SessionDTO> createSession(
            @RequestBody @Valid CreateSessionParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var session = sessionService.CreateSession(parameters);
        return ResponseEntity.ok(session.asSessionDTO());
    }

}
