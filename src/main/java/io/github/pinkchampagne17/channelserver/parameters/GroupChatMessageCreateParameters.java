package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.entity.ChatMessageType;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class GroupChatMessageCreateParameters {
    private ChatMessageType type;

    @Size(max = 256)
    private String content;
}
