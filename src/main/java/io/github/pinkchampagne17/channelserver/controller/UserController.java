package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.UserCreateParameters;
import io.github.pinkchampagne17.channelserver.parameters.UserUpdateParameters;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{hashId}")
    public ResponseEntity<User> getUserByHashId(@PathVariable String hashId)
            throws ParameterInvalidException {

        var user = this.userService.getUserByHashId(hashId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody UserCreateParameters parameters,
            BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var user = this.userService.createUser(parameters);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{hashId}")
    public ResponseEntity<?> updateUser(
            HttpServletRequest request,
            @PathVariable String hashId,
            @RequestBody @Valid UserUpdateParameters parameters,
            BindingResult bindingResult) {

        var user = userService.getCurrentUser(request);
        if (!user.getHashId().equals(hashId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        parameters.setGid(user.getGid());
        this.userService.UpdateUser(parameters);

        return ResponseEntity.noContent().build();
    }

}
