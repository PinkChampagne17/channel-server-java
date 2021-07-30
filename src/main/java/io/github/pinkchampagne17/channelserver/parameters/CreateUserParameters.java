package io.github.pinkchampagne17.channelserver.parameters;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class CreateUserParameters {
    @Null
    private Long gid;

    @Null
    private String hashId;

    @Email
    private String email;

    @Size(min = 1, max = 32)
    private String username;

    @Size(min = 1, max = 32)
    private String name;

    @Size(min = 6, max = 32)
    private String password;
}
