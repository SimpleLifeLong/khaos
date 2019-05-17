package org.greece.mythology.uranus.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/user")
    public String getUserInfo() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}
