package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.ChatMessage;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessagePMQueryParameters;

import java.util.List;

public interface ChatMessageService {
    void createMessage(Long senderGid, ChatMessageCreateParameters parameters);
    List<ChatMessage> queryPrivateMessages(ChatMessagePMQueryParameters parameters);
}
