package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping("/c/myself/friends/{hashId}")
    public ResponseEntity<?> removeFriendship(HttpServletRequest request, @PathVariable String hashId) {
        var user = this.userService.getCurrentUser(request);
        var friend = this.userService.getUserByHashId(hashId);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }

        var areTheyFriend = this.friendshipService.AreTheyFriend(user.getGid(), friend.getGid());
        if (!areTheyFriend) {
            return ResponseEntity.notFound().build();
        }

        this.friendshipService.removeFriendship(user.getGid(), friend.getGid());

        return ResponseEntity.noContent().build();
    }

}
