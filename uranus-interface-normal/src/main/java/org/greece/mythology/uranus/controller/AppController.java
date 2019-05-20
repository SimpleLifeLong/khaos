package org.greece.mythology.uranus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/user")
    public String getUserInfo() {
        return "USER-INFO:" + UUID.randomUUID().toString();
    }
}
