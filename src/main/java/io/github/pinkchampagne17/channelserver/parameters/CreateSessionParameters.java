package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.util.ValidationRegexStrings;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateSessionParameters {
    @NotNull
    @Size(min = 1, max = 320)
    private String usernameOrEmail;

    @NotNull
    @Pattern(regexp = ValidationRegexStrings.PASSWORD)
    private String password;

    @NotNull
    @Size(min = 1, max = 32)
    private String client;

    @NotNull
    @Size(min = 1, max = 256)
    private String device;

}
