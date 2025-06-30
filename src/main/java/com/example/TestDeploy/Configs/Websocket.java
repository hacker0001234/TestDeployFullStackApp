package com.example.TestDeploy.Configs;

import com.example.TestDeploy.Services.WebSocketServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebFlux
public class Websocket {
    private final WebSocketServiceHandler webSocketServiceHandler;

    public Websocket(WebSocketServiceHandler webSocketServiceHandler) {
        this.webSocketServiceHandler = webSocketServiceHandler;
    }

    @Bean
    public HandlerMapping websocketMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();

        map.put("/ws/message",webSocketServiceHandler);
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(-1);
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

}
