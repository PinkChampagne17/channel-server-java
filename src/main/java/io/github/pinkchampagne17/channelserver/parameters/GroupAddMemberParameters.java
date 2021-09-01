package io.github.pinkchampagne17.channelserver.parameters;

import io.github.pinkchampagne17.channelserver.entity.GroupMemberType;
import lombok.Data;

@Data
public class GroupAddMemberParameters {
    private Long groupGid;
    private Long userGid;
    private GroupMemberType type;
}
