package org.greece.mythology.uranus.repository;

import org.greece.mythology.tartarus.beans.entity.PreventionAlarmRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PreventionAlarmRuleRepository extends JpaRepository<PreventionAlarmRule, Long> {

    @Query(value = "select * from prevention_alarm_rule where id = ?1", nativeQuery = true)
    public PreventionAlarmRule getByIdAndStationIdLike(Long id);
}
