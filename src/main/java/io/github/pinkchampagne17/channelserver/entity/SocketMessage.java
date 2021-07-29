package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pinkchampagne17.channelserver.exception.SocketMessageDataIllegalException;
import lombok.Data;

@Data
public class SocketMessage {

    private String event;
    private Object data;

    private static ObjectMapper mapper = new ObjectMapper();

    public ChatMessage getDataAsChatMessage() throws SocketMessageDataIllegalException {
        try {
            return mapper.convertValue(this.data, ChatMessage.class);
        } catch (IllegalArgumentException e) {
            throw new SocketMessageDataIllegalException(e);
        }
    }
}
