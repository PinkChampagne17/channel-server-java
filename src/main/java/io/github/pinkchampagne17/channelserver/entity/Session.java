package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {
    @JsonIgnore
    private Long gid;

    @JsonIgnore
    private String hashId;

    private String client;
    private String device;
    private String session;
    private LocalDateTime expires;
}
