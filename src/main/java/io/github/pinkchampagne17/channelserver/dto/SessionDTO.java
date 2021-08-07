package io.github.pinkchampagne17.channelserver.dto;

import io.github.pinkchampagne17.channelserver.entity.SessionBase;
import io.github.pinkchampagne17.channelserver.util.ISO8601;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDTO extends SessionBase {
    private LocalDateTime expires;

    public String getExpires() {
        return ISO8601.of(this.expires);
    }
}
