package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.RequestCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;
import io.github.pinkchampagne17.channelserver.repository.FriendshipRepository;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.RequestService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public List<Request> getRequestsByGid(Long gid) {
        return this.requestRepository.getRequestsByGid(gid);
    }

    @Override
    public Request getRequestByGid(Long applicantGid, Long targetGid) {
        return this.requestRepository.getRequestByGid(applicantGid, targetGid);
    }

    @Override
    public void createOrUpdateRequest(Request request) {
        var areTheyFriend = this.friendshipService.AreTheyFriend(request.getApplicantGid(), request.getTargetGid());
        if (areTheyFriend) {
            throw new ParameterInvalidException("You are already friend.");
        }

        this.requestRepository.createOrUpdateRequest(request);
    }

    @Override
    public void updateStatus(RequestStatusUpdateParameters parameters) {
        var request = this.getRequestByGid(
                parameters.getApplicantGid(),
                parameters.getTargetGid()
        );
        if (request == null) {
            throw new ParameterInvalidException("The Request not exists.");
        }

        var txStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            this.requestRepository.updateStatus(parameters);

            // If update status of the request from WAITING to ACCEPTED
            if (request.getStatus() == RequestStatus.WAITING && parameters.getStatus() == RequestStatus.ACCEPTED) {
                this.friendshipRepository.createFriendship(parameters.getApplicantGid(), parameters.getTargetGid());
            }

            this.transactionManager.commit(txStatus);
        } catch (Exception e) {
            this.transactionManager.rollback(txStatus);
            throw e;
        }
    }

}
