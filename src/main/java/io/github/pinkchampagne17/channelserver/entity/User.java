package io.github.pinkchampagne17.channelserver.entity;

import lombok.Data;

@Data
public class User extends GidBase {
    private String email;
    private String password;
    private String nickname;
    private String bio;
    private String photoMd5;
    private Boolean isOnline;
}
