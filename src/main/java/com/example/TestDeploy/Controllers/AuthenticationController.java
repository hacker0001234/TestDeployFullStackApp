package com.example.TestDeploy.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @GetMapping("/check")
    public boolean checkAuthentication(@AuthenticationPrincipal OAuth2User user) {
        return (user != null);
    }
}
