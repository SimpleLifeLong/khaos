package org.greece.mythology.uranus.controller;

import org.greece.mythology.tartarus.commons.LocalPageRequest;
import org.greece.mythology.tartarus.commons.ResponseEntity;
import org.greece.mythology.uranus.service.PreventionAlarmRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@Validated
public class AppController {

    @Autowired
    private PreventionAlarmRuleService service;

    @PostMapping("/user")
    public ResponseEntity getUserInfo() {
        return ResponseEntity.ok(service.getAll(Pageable.unpaged()));
    }

    @PostMapping("/user/page")
    public ResponseEntity getUserInfo222(@RequestBody LocalPageRequest pageable) {
        return ResponseEntity.ok(service.getAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())));
    }
}
