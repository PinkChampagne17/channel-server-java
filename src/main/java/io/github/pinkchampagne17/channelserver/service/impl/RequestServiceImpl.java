package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;
import io.github.pinkchampagne17.channelserver.parameters.UpdateRequestStatusParameters;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
import io.github.pinkchampagne17.channelserver.service.FriendService;
import io.github.pinkchampagne17.channelserver.service.RequestService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getRequestsByGid(Long gid) {
        return this.requestRepository.getRequestsByGid(gid);
    }

    @Override
    public Request getRequestsByApplicantGidAndTargetGid(Long applicantGid, Long targetGid) {
        return this.requestRepository.getRequestsByApplicantGidAndTargetGid(applicantGid, targetGid);
    }

    @Override
    public void createOrUpdateRequest(CreateRequestParameters parameters) {
        // (NOT IMPLEMENTED) If the request already exists and got ACCEPTED, throw an exception.
        this.requestRepository.createOrUpdateRequest(parameters);
    }

    @Override
    public void updateStatus(UpdateRequestStatusParameters parameters) {
        var request = this.getRequestsByApplicantGidAndTargetGid(
                parameters.getApplicantGid(),
                parameters.getTargetGid()
        );
        if (request == null) {
            throw new ParameterInvalidException("The Request not exists.");
        }

        this.requestRepository.updateStatus(parameters);

        if (parameters.getStatus() == RequestStatus.ACCEPTED) {
            this.friendService.createFriendship(parameters.getApplicantGid(), parameters.getTargetGid());
        }
    }


}
