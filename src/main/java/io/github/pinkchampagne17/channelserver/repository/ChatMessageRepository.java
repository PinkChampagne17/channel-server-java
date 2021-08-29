package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.ChatMessage;
import io.github.pinkchampagne17.channelserver.parameters.ChatMessageQueryParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatMessageRepository {
    int createMessage(ChatMessage message);
    List<ChatMessage> queryChatMessages(ChatMessageQueryParameters parameters);
}
