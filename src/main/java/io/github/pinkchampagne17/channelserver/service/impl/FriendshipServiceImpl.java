package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import io.github.pinkchampagne17.channelserver.repository.FriendshipRepository;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public void createFriendship(Long gid, Long friendGid) {
        this.friendshipRepository.createFriendship(gid, friendGid);
        this.friendshipRepository.createFriendship(friendGid, gid);
    }

    @Override
    public boolean AreTheyFriend(Long gid, Long friendGid) {
        var friendship = this.friendshipRepository.getFriendship(gid, friendGid);
        return friendship != null;
    }

}
