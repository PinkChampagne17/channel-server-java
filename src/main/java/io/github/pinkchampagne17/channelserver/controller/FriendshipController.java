package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FriendshipController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/c/myself/friends")
    public ResponseEntity<List<Friendship>> getFriendships(HttpServletRequest request) {
        var currentUser = this.userService.getCurrentUser(request);

        var friendships = this.friendshipService.getFriendships(currentUser.getGid());

        return ResponseEntity.ok(friendships);
    }

}
