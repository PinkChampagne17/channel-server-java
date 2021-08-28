package io.github.pinkchampagne17.channelserver.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatMessage {
    private String ChatId;
    private Long messageId;
    private Long senderGid;
    private ChatMessageType type;
    private String content;
    private String createAt;
    private String updateAt;
}
