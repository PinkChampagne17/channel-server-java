package io.github.pinkchampagne17.channelserver.entity;

import lombok.Data;

@Data
public class GroupMember extends User {
    private GroupMemberType type;
}
