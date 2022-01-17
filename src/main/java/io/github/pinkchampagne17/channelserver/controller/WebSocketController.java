package io.github.pinkchampagne17.channelserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.pinkchampagne17.channelserver.entity.SocketMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketController extends TextWebSocketHandler {

    private static final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);

        var onlineMessage = session.getId() + " has just got online!";

        broadcastTextMessage(onlineMessage);
        System.out.println(onlineMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());

        var offlineMessage = session.getId() + " has just got offline.";

        broadcastTextMessage(offlineMessage);
        System.out.println(offlineMessage);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        var mapper = new ObjectMapper();
        var socketMessage =  mapper.readValue("{\"event\":\"message\",\"data\":\"Hello\"}", SocketMessage.class);
//        System.out.println(socketMessage.getDataAsChatMessage().getMessage());
//        broadcastTextMessage(session.getId() + ": " + message.getPayload());
    }

    public static void broadcastTextMessage(String message) {
        sessions.forEach((id, session) -> {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
