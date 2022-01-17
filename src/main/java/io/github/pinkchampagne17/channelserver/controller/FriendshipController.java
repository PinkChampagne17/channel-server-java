package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.Friendship;
import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.service.FriendshipService;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendshipController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/users/me/friends")
    public ResponseEntity<List<Friendship>> getFriendships(@RequestAttribute("user") User currentUser) {
        var friendships = this.friendshipService.getFriendships(currentUser.getGid());
        return ResponseEntity.ok(friendships);
    }

    @DeleteMapping("/users/me/friends/{hashId}")
    public ResponseEntity<?> removeFriendship(
            @RequestAttribute("user") User currentUser,
            @PathVariable String hashId
    ) {
        var friend = this.userService.getUserByHashId(hashId);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }

        var areTheyFriend = this.friendshipService.AreTheyFriend(currentUser.getGid(), friend.getGid());
        if (!areTheyFriend) {
            return ResponseEntity.notFound().build();
        }

        this.friendshipService.removeFriendship(currentUser.getGid(), friend.getGid());

        return ResponseEntity.noContent().build();
    }

}
