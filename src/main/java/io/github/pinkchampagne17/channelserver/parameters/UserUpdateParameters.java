package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.util.ValidationRegexStrings;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserUpdateParameters {
    @Null
    private Long gid;

    @Email
    private String email;

    @Pattern(regexp = ValidationRegexStrings.LINK)
    private String username;

    @Pattern(regexp = ValidationRegexStrings.PASSWORD)
    private String password;

    @Size(min = 1, max = 32)
    private String nickname;

    @Size(max = 128)
    private String bio;
}
