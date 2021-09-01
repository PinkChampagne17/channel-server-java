package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GidBase {
    @JsonIgnore
    private Long gid;

    @JsonProperty("id")
    private String hashId;

    private String link;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
