package org.greece.mythology.uranus.controller;

import org.greece.mythology.uranus.service.PreventionAlarmRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private PreventionAlarmRuleService service;

    @GetMapping("/user/{id}")
    public Object getUserInfo(@PathVariable Long id) {

        return service.getById(id);
    }
}
