package io.github.pinkchampagne17.channelserver.configuration;

import io.github.pinkchampagne17.channelserver.controller.WebSocketController;
import io.github.pinkchampagne17.channelserver.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketController(), "/ws")
                .addInterceptors(new WebSocketInterceptor());
    }

    @Bean
    public WebSocketHandler webSocketController() {
        return new WebSocketController();
    }

}
