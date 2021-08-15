package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FriendshipRepository {
    int createFriendship(Long gid, Long friendGid);
    Friendship getFriendship(Long gid, Long friendGid);
}
