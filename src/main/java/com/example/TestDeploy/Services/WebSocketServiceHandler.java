package com.example.TestDeploy.Services;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketServiceHandler implements WebSocketHandler {
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        String email = getEmailFromQuery(webSocketSession);
        userSessions.put(email, webSocketSession);

        return webSocketSession.receive()
                .flatMap(websocketMessageText -> {
                    String message = email + " : " + websocketMessageText.getPayloadAsText();

                    return webSocketSession.send(Mono.just(webSocketSession.textMessage(message)));
                })
                .doFinally(signalType -> userSessions.remove(email))
                .then();
    }

    private String getEmailFromQuery(WebSocketSession session) {
        return session.getHandshakeInfo().getUri().getQuery().split("=")[1];
    }

}
