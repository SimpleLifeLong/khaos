package org.greece.mythology.pontus.controller;

import org.greece.mythology.erebus.uranus.UranusFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeignController {

    @Autowired
    UranusFeignService uranusFeignService;

    @GetMapping("/web/user")
    public String getAll() {
        return uranusFeignService.getUser();
    }
}
