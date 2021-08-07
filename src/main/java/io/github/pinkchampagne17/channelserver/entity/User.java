package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @JsonIgnore
    private Long gid;

    @JsonProperty("id")
    private String hashId;

    private String email;
    private String username;
    private String password;
    private String name;
    private String bio;
    private String photoMd5;
    private Boolean isOnline;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
