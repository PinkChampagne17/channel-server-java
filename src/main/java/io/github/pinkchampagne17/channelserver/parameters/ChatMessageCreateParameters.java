package io.github.pinkchampagne17.channelserver.parameters;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.github.pinkchampagne17.channelserver.entity.ChatMessageType;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ChatMessageCreateParameters {

    @JsonAlias("receiverId")
    private String receiverHashId;

    private ChatMessageType type;

    @Size(max = 256)
    private String content;

}
