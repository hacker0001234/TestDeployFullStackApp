package com.example.TestDeploy.Controllers;

import com.example.TestDeploy.Services.MainService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/test")
    public Mono<String> getEmail(@AuthenticationPrincipal OAuth2User user) {
        String email = user.getAttribute("email");
        return Mono.just(email);
    }

    @GetMapping(value = ("/sse"), produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> getEmailBySSE(@AuthenticationPrincipal OAuth2User user) {
        return mainService.getEmailBySSE(user);
    }
}
