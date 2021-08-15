package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Friendship;

import java.util.List;

public interface FriendshipService {
    void createFriendship(Long gid, Long friendGid);
    boolean AreTheyFriend(Long gid, Long friendGid);
    List<Friendship> getFriendships(Long gid);
    void removeFriendship(Long gid, Long friendGid);
}
