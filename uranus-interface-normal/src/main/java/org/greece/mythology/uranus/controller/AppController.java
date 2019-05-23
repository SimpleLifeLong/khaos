package org.greece.mythology.uranus.controller;

import org.greece.mythology.tartarus.beans.entity.PreventionAlarmRule;
import org.greece.mythology.tartarus.commons.ResponseEntity;
import org.greece.mythology.uranus.service.PreventionAlarmRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/app")
@Validated
public class AppController {

    @Autowired
    private PreventionAlarmRuleService service;

    @GetMapping("/user")
    public ResponseEntity getUserInfo(@RequestParam @NotNull(message = "id 不能为空") Long id) {
        PreventionAlarmRule alarmRule = service.getById(id);
//        return   ResponseEntity.badRequest().body("iiiiiii");
        return ResponseEntity.ok(alarmRule);
    }
}
