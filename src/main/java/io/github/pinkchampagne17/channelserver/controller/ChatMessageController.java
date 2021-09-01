package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.ChatMessage;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessagePMQueryParameters;
import io.github.pinkchampagne17.channelserver.parameters.GroupChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.service.ChatMessageService;
import io.github.pinkchampagne17.channelserver.service.GroupService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ChatMessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

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

    @PostMapping("/groups/{hashId}/messages")
    public ResponseEntity<?> createGroupMessage(
            @RequestAttribute(name = "user") User currentUser,
            @PathVariable String hashId,
            @RequestBody @Valid GroupChatMessageCreateParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var group = this.groupService.queryGroupByHashId(hashId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        var isUserInGroup = this.groupService.isUserInGroup(group.getGid(), currentUser.getGid());
        if (!isUserInGroup) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        this.chatMessageService.createGroupMessage(group.getGid(), currentUser.getGid(), parameters);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/c/me/friends/{targetUserHashId}/messages")
    public ResponseEntity<List<ChatMessage>> queryPrivateChatMessage(
            @RequestAttribute(name = "user") User currentUser,
            @PathVariable String targetUserHashId
    ) {
        var targetUser = this.userService.getUserByHashId(targetUserHashId);
        if (targetUser == null) {
            return ResponseEntity.notFound().build();
        }

        var queryParams = new ChatMessagePMQueryParameters() {{
            setUserGid(currentUser.getGid());
            setAnotherUserGid(targetUser.getGid());
        }};
        var messages = this.chatMessageService.queryPrivateMessages(queryParams);

        return ResponseEntity.ok(messages);
    }

}
