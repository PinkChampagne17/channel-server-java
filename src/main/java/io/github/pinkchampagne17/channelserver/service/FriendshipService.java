package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Friendship;

public interface FriendshipService {
    void createFriendship(Long gid, Long friendGid);
    boolean AreTheyFriend(Long gid, Long friendGid);
}
