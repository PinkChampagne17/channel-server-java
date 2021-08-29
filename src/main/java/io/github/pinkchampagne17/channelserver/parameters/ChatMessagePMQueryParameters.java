package io.github.pinkchampagne17.channelserver.parameters;

import lombok.Data;

@Data
public class ChatMessagePMQueryParameters {
    private Long userGid;
    private Long anotherUserGid;
}
