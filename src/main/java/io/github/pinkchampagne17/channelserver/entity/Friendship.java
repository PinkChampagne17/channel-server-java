package io.github.pinkchampagne17.channelserver.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Friendship {
    private Long gid;
    private Long friendGid;
    private LocalDateTime createAt;
}
