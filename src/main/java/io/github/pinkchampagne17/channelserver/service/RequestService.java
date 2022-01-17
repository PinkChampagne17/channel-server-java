package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;

import java.util.List;

public interface RequestService {
    List<Request> getRequestsByGid(Long gid);
    Request getRequestByGid(Long applicantGid, Long targetGid);
    void createOrUpdateRequest(Request request);
    void updateStatus(RequestStatusUpdateParameters parameters);
}
