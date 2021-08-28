package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.parameters.ChatMessageCreateParameters;

public interface ChatMessageService {
    void createMessage(Long senderGid, ChatMessageCreateParameters parameters);
}
