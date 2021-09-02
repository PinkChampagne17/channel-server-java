package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.GroupMemberType;
import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import io.github.pinkchampagne17.channelserver.exception.NotFoundException;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.GroupAddMemberParameters;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;
import io.github.pinkchampagne17.channelserver.repository.FriendshipRepository;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.GroupService;
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
    private GroupService groupService;

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

        var isUserInGroup = this.groupService.isUserInGroup(request.getTargetGid(), request.getApplicantGid());
        if (isUserInGroup) {
            throw new ParameterInvalidException("You are already in this group.");
        }

        this.requestRepository.createOrUpdateRequest(request);
    }

    @Override
    public void updateStatus(RequestStatusUpdateParameters parameters) {
        var requestBeforeUpdate = this.getRequestByGid(
                parameters.getApplicantGid(),
                parameters.getTargetGid()
        );
        if (requestBeforeUpdate == null) {
            throw new NotFoundException();
        }

        var txStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            this.requestRepository.updateStatus(parameters);

            // Create friendship after status updated form WAITING to ACCEPTED
            // Or
            // Make applicant be member of this group
            var targetGidIsUser = this.userService.getUserByGid(parameters.getTargetGid()) != null;
            var hasStatusBeenUpdatedFromWaitingToAccepted = (
                    requestBeforeUpdate.getStatus() == RequestStatus.WAITING &&
                            parameters.getStatus() == RequestStatus.ACCEPTED
            );
            if (targetGidIsUser && hasStatusBeenUpdatedFromWaitingToAccepted) {
                this.friendshipRepository.createFriendship(parameters.getApplicantGid(), parameters.getTargetGid());
            } else {
                this.groupService.addMember(new GroupAddMemberParameters() {{
                    setGroupGid(parameters.getTargetGid());
                    setUserGid(parameters.getApplicantGid());
                    setType(GroupMemberType.MEMBER);
                }});
            }

            this.transactionManager.commit(txStatus);
        } catch (Exception e) {
            this.transactionManager.rollback(txStatus);
            throw e;
        }
    }

}
