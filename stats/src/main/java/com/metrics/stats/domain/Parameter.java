package com.metrics.stats.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parameter {

    private String machineId;
    private String name;
    private LocalDateTime reportedTime;
    private Double value;

    public Parameter(String machineId, String name, LocalDateTime reportedTime, Double value) {
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

    public Double getValue() {
        return value;
    }

    public static class Builder {
        private String machineKey;
        private Map<String, Double> parametersMap;
        private LocalDateTime receivedTime;

        public Builder setMachineKey(String machineKey) {
            this.machineKey = machineKey;
            return this;
        }

        public Builder setParameters(Map<String, Double> parameters) {
            this.parametersMap = parameters;
            return this;
        }

        public Builder setReceivedTime(LocalDateTime receivedTime) {
            this.receivedTime = receivedTime;
            return this;
        }


        public List<Parameter> build() {
            List<Parameter> params = new ArrayList<>();

            if(parametersMap != null && !parametersMap.isEmpty()) {
                parametersMap.forEach((key, value) -> {
                   params.add(new Parameter(this.machineKey, key, this.receivedTime, value));
                });
            }

            return params;
        }
    }
}
