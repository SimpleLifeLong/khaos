package org.greece.mythology.tartarus.beans.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by generator on 2018-11-08 14:32:24
 */
@Data
@Entity
public class PreventionAlarmRule {
    @Id
    @GeneratedValue
    private Long id;
    private String stationId;
    private java.util.Date updateTime;
}
