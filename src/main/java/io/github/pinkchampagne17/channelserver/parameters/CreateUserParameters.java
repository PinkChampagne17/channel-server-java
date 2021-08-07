package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.util.RegexStrings;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserParameters {
    @Null
    private Long gid;

    @Null
    private String hashId;

    @Email
    private String email;

    @Pattern(regexp = RegexStrings.USERNAME)
    private String username;

    @Size(min = 1, max = 32)
    private String name;

    @Pattern(regexp = RegexStrings.PASSWORD)
    private String password;
}
