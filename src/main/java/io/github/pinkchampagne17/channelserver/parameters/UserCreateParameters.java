package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.util.ValidationRegexStrings;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserCreateParameters {
    @Null
    private Long gid;

    @Null
    private String hashId;

    @Email
    private String email;

    @Pattern(regexp = ValidationRegexStrings.LINK)
    private String link;

    @Size(min = 1, max = 32)
    private String nickname;

    @Pattern(regexp = ValidationRegexStrings.PASSWORD)
    private String password;
}
