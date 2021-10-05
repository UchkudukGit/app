package ru.nikonov.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikonov.domain.ProfileResponse;

@RestController
public class MainController {

    @Value("${spring.profiles.active:${spring.profiles.default}}")
    private String activeProfile;

    @GetMapping("/profile")
    public ProfileResponse getProfile() {
        System.out.println(activeProfile);
        return new ProfileResponse(activeProfile);
    }

    @GetMapping("/close")
    public String close() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }).start();
        return "application was closed";
    }
}
