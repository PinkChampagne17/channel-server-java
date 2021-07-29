package io.github.pinkchampagne17.channelserver.service;

import org.springframework.stereotype.Service;

@Service
public class MockService {

    public void Print(String message) {
        System.out.println("Mock Service: " + message);
    }

}
