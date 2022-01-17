package io.github.pinkchampagne17.channelserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User extends GidBase {
    @JsonIgnore
    private String password;

    private String email;
    private String nickname;
    private String bio;
    private String photoMd5;
    private Boolean isOnline;
}
