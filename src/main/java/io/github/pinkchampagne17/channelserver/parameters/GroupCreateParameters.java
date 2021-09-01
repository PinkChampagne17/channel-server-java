package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.util.HashId;
import io.github.pinkchampagne17.channelserver.util.ValidationRegexStrings;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class GroupCreateParameters {
    @Size(min = 1, max = 32)
    private String name;

    @Pattern(regexp = ValidationRegexStrings.LINK)
    private String link;

    @Size(max = 128)
    private String bio;

    public Group asGroup() {
        var group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }
}
