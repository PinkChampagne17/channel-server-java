package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateRequestParameters;
import io.github.pinkchampagne17.channelserver.service.RequestService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @GetMapping("/c/{hashId}/requests")
    public ResponseEntity<List<Request>> getRequestsByHashId(
            HttpServletRequest request,
            @PathVariable String hashId
    ) {
        var currentUser = userService.getCurrentUser(request);
        if (!currentUser.getHashId().equals(hashId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var requests = requestService.getRequestsByGid(currentUser.getGid());
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/requests")
    public ResponseEntity<?> createRequest(
            @RequestBody @Valid CreateRequestParameters parameters,
            BindingResult bindingResult,
            HttpServletRequest servletRequest
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var currentUser = userService.getCurrentUser(servletRequest);
        var targetUser = userService.getUserByHashId(parameters.getTargetHashId());

        parameters.setApplicantGid(currentUser.getGid());
        parameters.setTargetGid(targetUser.getGid());

        requestService.createOrUpdateRequest(parameters);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
