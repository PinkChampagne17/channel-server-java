package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import io.github.pinkchampagne17.channelserver.repository.FriendshipRepository;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public void createFriendship(Long gid, Long friendGid) {
        this.friendshipRepository.createFriendship(gid, friendGid);
    }

    @Override
    public boolean AreTheyFriend(Long gid, Long friendGid) {
        var friendship = this.friendshipRepository.getFriendshipByGidAndFriendGid(gid, friendGid);
        return friendship != null;
    }

    @Override
    public List<Friendship> getFriendships(Long gid) {
        return this.friendshipRepository.getFriendshipsByGid(gid);
    }

    @Override
    public void removeFriendship(Long gid, Long friendGid) {
        var txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            this.friendshipRepository.removeFriendship(gid, friendGid);
            this.requestRepository.removeRequest(gid, friendGid);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
    }

}
