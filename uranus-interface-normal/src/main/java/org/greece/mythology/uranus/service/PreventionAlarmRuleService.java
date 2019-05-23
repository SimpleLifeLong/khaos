package org.greece.mythology.uranus.service;

import org.greece.mythology.tartarus.beans.entity.PreventionAlarmRule;
import org.greece.mythology.uranus.repository.PreventionAlarmRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PreventionAlarmRuleService {

    public final PreventionAlarmRuleRepository repository;

    @Autowired
    public PreventionAlarmRuleService(PreventionAlarmRuleRepository repository) {
        this.repository = repository;
    }


    public PreventionAlarmRule getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PreventionAlarmRule findByIdOrderByStationIdDesc(Long id) {
        return repository.findByIdOrderByStationIdDesc(id);
    }

    public Page getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
