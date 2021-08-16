package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.RequestCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;
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
    public ResponseEntity<?> createOrUpdateRequest(
            @RequestBody @Valid RequestCreateParameters parameters,
            BindingResult bindingResult,
            HttpServletRequest servletRequest
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var currentUser = userService.getCurrentUser(servletRequest);
        var targetUser = userService.getUserByHashId(parameters.getTargetHashId());
        if (targetUser == null) {
            throw new ParameterInvalidException("The target user not exists.");
        }

        parameters.setApplicantGid(currentUser.getGid());
        parameters.setTargetGid(targetUser.getGid());

        requestService.createOrUpdateRequest(parameters);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/c/{hashId}/requests/{applicantHashId}")
    public ResponseEntity<?> updateRequestStatus(
            HttpServletRequest servletRequest,
            @PathVariable(name = "hashId") String HashId,
            @PathVariable(name = "applicantHashId") String applicantHashId,
            @RequestBody @Valid RequestStatusUpdateParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        if (parameters.getStatus() == RequestStatus.WAITING) {
            throw new ParameterInvalidException("The status can not be 'WAITING'.");
        }

        var currentUser = userService.getCurrentUser(servletRequest);
        if (!currentUser.getHashId().equals(HashId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var applicant = userService.getUserByHashId(applicantHashId);
        if (applicant == null) {
            throw new ParameterInvalidException("The target user not exists.");
        }

        parameters.setApplicantGid(applicant.getGid());
        parameters.setTargetGid(currentUser.getGid());

        requestService.updateStatus(parameters);

        return ResponseEntity.noContent().build();
    }

}
