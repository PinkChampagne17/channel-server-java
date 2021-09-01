package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.entity.GroupMember;
import io.github.pinkchampagne17.channelserver.parameters.GroupAddMemberParameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupRepository {
    int createGroup(Group group);
    int addMember(GroupAddMemberParameters parameters);
    Group queryGroupByGid(Long gid);
    List<Group> queryGroupByUserGid(Long gid);
    List<GroupMember> queryMembersOfGroup(Long gid);
    boolean isUserInGroup(Long groupGid, Long userGid);
}
