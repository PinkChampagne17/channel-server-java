package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;
import io.github.pinkchampagne17.channelserver.parameters.UpdateRequestStatusParameters;

import java.util.List;

public interface RequestService {
    List<Request> getRequestsByGid(Long gid);
    Request getRequestsByApplicantGidAndTargetGid(Long applicantGid, Long targetGid);
    void createOrUpdateRequest(CreateRequestParameters parameters);
    void updateStatus(UpdateRequestStatusParameters parameters);
}
