package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Request;
import io.github.pinkchampagne17.channelserver.entity.RequestStatus;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.RequestCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.RequestStatusUpdateParameters;
import io.github.pinkchampagne17.channelserver.service.GroupService;
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
import java.util.Objects;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/c/{hashId}/requests")
    public ResponseEntity<List<Request>> getRequestsByHashId(
            @RequestAttribute("user") User currentUser,
            @PathVariable String hashId
    ) {
        var isTargetCurrentUser = currentUser.getHashId().equals(hashId);
        if (isTargetCurrentUser) {
            var requests = requestService.getRequestsByGid(currentUser.getGid());
            return ResponseEntity.ok(requests);
        }

        var targetUser = this.userService.getUserByHashId(hashId);
        var targetGroup = this.groupService.queryGroupByHashId(hashId);

        if (targetUser == null && targetGroup == null) {
            return ResponseEntity.notFound().build();
        }
        if (targetUser != null || !this.groupService.isUserOwnerOrAdmin(targetGroup.getGid(), currentUser.getGid())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var requests = this.requestService.getRequestsByGid(targetGroup.getGid());
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/c/{hashId}/requests")
    public ResponseEntity<?> createOrUpdateRequest(
            @RequestAttribute("user") User currentUser,
            @RequestBody @Valid RequestCreateParameters parameters,
            @PathVariable String hashId,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var targetUser = this.userService.getUserByHashId(hashId);
        var targetGroup = this.groupService.queryGroupByHashId(hashId);

        if (targetUser == null && targetGroup == null) {
            return ResponseEntity.notFound().build();
        }

        var request = Request.builder()
                .applicantGid(currentUser.getGid())
                .targetGid(Objects.requireNonNullElse(targetUser, targetGroup).getGid())
                .reason(parameters.getReason())
                .build();

        // this method has a bug
        requestService.createOrUpdateRequest(request);

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
            throw new ParameterInvalidException("The status can not be updated to 0 or 'WAITING'.");
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
