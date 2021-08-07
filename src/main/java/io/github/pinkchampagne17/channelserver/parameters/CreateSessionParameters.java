package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.entity.Session;
import io.github.pinkchampagne17.channelserver.util.RegexStrings;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateSessionParameters {
    @NotNull
    @Size(min = 1, max = 320)
    private String usernameOrEmail;

    @NotNull
    @Pattern(regexp = RegexStrings.PASSWORD)
    private String password;

    @NotNull
    @Size(min = 1, max = 32)
    private String client;

    @NotNull
    @Size(min = 1, max = 32)
    private String device;

    public Session asSession() {
        var session = new Session();
        BeanUtils.copyProperties(this, session);
        return session;
    }
}
