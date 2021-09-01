package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.parameters.GroupCreateParameters;

public interface GroupService {
    Group createGroup(User owner, GroupCreateParameters parameters);
    Group queryGroupByGid(Long gid);
    Group queryGroupByHashId(String hashId);
    boolean isUserInGroup(Long groupGid, Long userGid);
}
