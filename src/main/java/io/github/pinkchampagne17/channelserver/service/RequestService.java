package io.github.pinkchampagne17.channelserver.service;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;

import java.util.List;

public interface RequestService {
    List<Request> getRequestsByGid(Long gid);
    void createOrUpdateRequest(CreateRequestParameters parameters);
}
