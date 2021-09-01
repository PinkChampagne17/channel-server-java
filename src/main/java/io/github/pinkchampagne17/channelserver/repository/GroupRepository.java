package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.parameters.GroupAddMemberParameters;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface GroupRepository {
    int createGroup(Group group);
    int addMember(GroupAddMemberParameters parameters);
    Group queryGroupByGid(Long gid);
    List<Group> queryGroupByUserGid(Long gid);
    boolean isUserInGroup(@Param("groupGid") Long groupGid, @Param("userGid") Long userGid);
}
