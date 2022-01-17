package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {
    @JsonIgnore
    private Long gid;

    @JsonProperty("id")
    private String hashId;

    private String client;
    private String device;
    private String session;
    private LocalDateTime expires;
}
