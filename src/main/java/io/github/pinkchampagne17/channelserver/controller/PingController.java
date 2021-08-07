package io.github.pinkchampagne17.channelserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public ResponseEntity<Object> Ping() throws Exception {

        var res = new Object() {
            public final String message = "pong";
        };

        return ResponseEntity.ok(res);
    }

}
