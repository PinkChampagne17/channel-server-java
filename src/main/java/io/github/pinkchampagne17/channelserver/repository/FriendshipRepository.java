package io.github.pinkchampagne17.channelserver.repository;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendshipRepository {
    int createFriendship(Long gid, Long friendGid);
    Friendship getFriendshipByGidAndFriendGid(Long gid, Long friendGid);
    List<Friendship> getFriendshipsByGid(Long gid);
}
