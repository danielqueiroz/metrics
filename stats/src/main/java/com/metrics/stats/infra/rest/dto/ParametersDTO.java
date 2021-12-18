package com.metrics.stats.infra.rest.dto;

import java.io.Serializable;
import java.util.Map;

public class ParametersDTO implements Serializable {

    private String machineKey;
    private Map<String, Long> parameters;

    public String getMachineKey() {
        return machineKey;
    }

    public void setMachineKey(String machineKey) {
        this.machineKey = machineKey;
    }

    public Map<String, Long> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Long> parameters) {
        this.parameters = parameters;
    }
}