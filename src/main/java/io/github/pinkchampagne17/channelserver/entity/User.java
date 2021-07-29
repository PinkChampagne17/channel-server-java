package io.github.pinkchampagne17.channelserver.entity;

import io.github.pinkchampagne17.channelserver.util.HashId;
import io.github.pinkchampagne17.channelserver.util.ISO8601;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String name;
    private String bio;
    private String photoMd5;
    private Boolean isOnline;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public String getId() {
        return HashId.encodeOne(this.id);
    }

    public String getCreateAt() {
        return ISO8601.of(this.createAt);
    }

    public String getUpdateAt() {
        return ISO8601.of(this.updateAt);
    }
}
