package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.service.ChatMessageService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ChatMessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/chat-messages")
    public ResponseEntity<?> createMessage(
            HttpServletRequest request,
            @RequestBody @Valid ChatMessageCreateParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var currentUser = this.userService.getCurrentUser(request);

        var senderGid = currentUser.getGid();
        this.chatMessageService.createMessage(senderGid, parameters);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
