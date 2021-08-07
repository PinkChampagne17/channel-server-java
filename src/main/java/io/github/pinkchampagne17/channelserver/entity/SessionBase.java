package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionBase {
    @JsonIgnore
    private Long gid;

    @JsonProperty("gid")
    private String hashId;

    private String client;
    private String device;
    private String session;
}
