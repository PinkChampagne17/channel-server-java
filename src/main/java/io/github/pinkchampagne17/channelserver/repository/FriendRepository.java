package io.github.pinkchampagne17.channelserver.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendRepository {
    int createFriendship(Long gid, Long friendGid);
}
