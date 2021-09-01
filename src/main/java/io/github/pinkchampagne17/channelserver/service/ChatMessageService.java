package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.ChatMessage;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessagePMQueryParameters;
import io.github.pinkchampagne17.channelserver.parameters.GroupChatMessageCreateParameters;

import java.util.List;

public interface ChatMessageService {
    void createMessage(Long senderGid, ChatMessageCreateParameters parameters);
    void createGroupMessage(Long groupGid, Long senderGid, GroupChatMessageCreateParameters parameters);
    List<ChatMessage> queryPrivateMessages(ChatMessagePMQueryParameters parameters);
}
