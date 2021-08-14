package io.github.pinkchampagne17.channelserver.controller;

import io.github.pinkchampagne17.channelserver.entity.User;
import io.github.pinkchampagne17.channelserver.exception.ParameterInvalidException;
import io.github.pinkchampagne17.channelserver.parameters.CreateUserParameters;
import io.github.pinkchampagne17.channelserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/myself")
    public ResponseEntity<User> getMyself(HttpServletRequest request) {
        var user = (User)request.getAttribute("user");
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @Valid @RequestBody CreateUserParameters parameters,
            BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new ParameterInvalidException(bindingResult);
        }

        var user = this.userService.createUser(parameters);
        return ResponseEntity.ok(user);
    }

}
