package io.github.pinkchampagne17.channelserver.parameters;

import lombok.Data;

@Data
public class GetUsersParameters {
    private Long gid;
    private String hashId;
    private String username;
    private String email;
    private String password;
    private Long count;
}
