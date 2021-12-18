package com.metrics.stats.domain;

import java.time.LocalDateTime;

public class Parameter {

    private String machineId;
    private String name;
    private LocalDateTime reportedTime;
    private Long value;

    public Parameter(String machineId, String name, LocalDateTime reportedTime, Long value) {
        this.machineId = machineId;
        this.name = name;
        this.reportedTime = reportedTime;
        this.value = value;
    }

    public String getMachineId() {
        return machineId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getReportedTime() {
        return reportedTime;
    }

    public Long getValue() {
        return value;
    }
}
