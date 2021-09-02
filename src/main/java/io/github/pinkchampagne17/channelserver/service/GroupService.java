package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.entity.GroupMember;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.GroupAddMemberParameters;
import io.github.pinkchampagne17.channelserver.parameters.GroupCreateParameters;

import java.util.List;

public interface GroupService {
    Group createGroup(User owner, GroupCreateParameters parameters);
    Group queryGroupByGid(Long gid);
    Group queryGroupByHashId(String hashId);
    List<Group> queryGroupByUserGid(Long gid);
    List<GroupMember> queryMembersOfGroup(Long gid);
    boolean isUserInGroup(Long groupGid, Long userGid);
    boolean isUserOwnerOrAdmin(Long groupGid, Long userGid);
    void addMember(GroupAddMemberParameters parameters);
    void removeMember(Long groupGid, Long userGid);
}
