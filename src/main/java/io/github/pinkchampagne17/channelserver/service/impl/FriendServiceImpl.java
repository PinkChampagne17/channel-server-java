package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.repository.FriendRepository;
import io.github.pinkchampagne17.channelserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;


    @Override
    public void createFriendship(Long gid, Long friendGid) {
        this.friendRepository.createFriendship(gid, friendGid);
        this.friendRepository.createFriendship(friendGid, gid);
    }
}
