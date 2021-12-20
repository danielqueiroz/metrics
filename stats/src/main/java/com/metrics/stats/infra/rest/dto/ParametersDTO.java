package com.metrics.stats.infra.rest.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class ParametersDTO implements Serializable {

    private String machineKey;
    private Map<String, Double> parameters;

    public String getMachineKey() {
        return machineKey;
    }

    public void setMachineKey(String machineKey) {
        this.machineKey = machineKey;
    }

    public Map<String, Double> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Double> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametersDTO that = (ParametersDTO) o;
        return machineKey.equals(that.machineKey) && parameters.equals(that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineKey, parameters);
    }
}