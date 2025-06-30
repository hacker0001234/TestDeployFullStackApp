package com.example.TestDeploy.Services;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MainService {

    public Flux<ServerSentEvent<String>> getEmailBySSE(OAuth2User user) {
        return Flux.just(ServerSentEvent.<String>builder().data(user.getAttribute("email")).build());
    }
}
