package io.github.pinkchampagne17.channelserver.service.impl;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;
import io.github.pinkchampagne17.channelserver.repository.RequestRepository;
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
    private RequestRepository requestRepository;

    @Override
    public List<Request> getRequestsByGid(Long gid) {
        return requestRepository.getRequestsByGid(gid);
    }

    @Override
    public void createOrUpdateRequest(CreateRequestParameters parameters) {
        var targetUser = userService.getUserByHashId(parameters.getTargetHashId());
        if (targetUser == null) {
            throw new ParameterInvalidException("The target user not exists.");
        }

        parameters.setTargetGid(targetUser.getGid());

        requestRepository.createOrUpdateRequest(parameters);
    }

}
