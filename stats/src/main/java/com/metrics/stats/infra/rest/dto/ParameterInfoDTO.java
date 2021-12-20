package com.metrics.stats.infra.rest.dto;

import java.io.Serializable;
import java.util.Objects;

public class ParameterInfoDTO implements Serializable {

    private String machineId;
    private String parameterName;
    private Double average;
    private Double median;
    private Double min;
    private Double max;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterInfoDTO that = (ParameterInfoDTO) o;
        return machineId.equals(that.machineId) && parameterName.equals(that.parameterName) && average.equals(that.average) && median.equals(that.median) && min.equals(that.min) && max.equals(that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(machineId, parameterName, average, median, min, max);
    }
}
