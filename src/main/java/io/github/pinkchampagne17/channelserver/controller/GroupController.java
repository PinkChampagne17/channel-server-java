package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Group;
import io.github.pinkchampagne17.channelserver.entity.GroupMember;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.GroupCreateParameters;
import io.github.pinkchampagne17.channelserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(
            @RequestAttribute("user") User currentUser,
            @Valid @RequestBody GroupCreateParameters parameters,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasGlobalErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }
        if (parameters.getBio() == null) {
            parameters.setBio("");
        }
        var group = this.groupService.createGroup(currentUser, parameters);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @GetMapping("/groups/{hashId}")
    public ResponseEntity<Group> queryGroupByHashId(@PathVariable String hashId) {
        var group = this.groupService.queryGroupByHashId(hashId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group);
    }

    @GetMapping("/users/me/groups")
    public ResponseEntity<List<Group>> queryGroupByUserGid(@RequestAttribute("user") User currentUser) {
        var gid = currentUser.getGid();
        var groups = this.groupService.queryGroupByUserGid(gid);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("groups/{hashId}/members")
    public ResponseEntity<List<GroupMember>> queryMembersOfGroup(
            @RequestAttribute("user") User currentUser,
            @PathVariable String hashId
    ) {
        var group = this.groupService.queryGroupByHashId(hashId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        if(!this.groupService.isUserInGroup(group.getGid(), currentUser.getGid())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var members = this.groupService.queryMembersOfGroup(group.getGid());
        return ResponseEntity.ok(members);
    }
}
