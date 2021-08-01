package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.interceptor.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        var user = this.userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserParameters parameters,
            BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        try {
            var user = this.userService.createUser(parameters);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new ParameterInvalidException("This username or email already exists.");
            } else {
                throw e;
            }
        }
    }

}
