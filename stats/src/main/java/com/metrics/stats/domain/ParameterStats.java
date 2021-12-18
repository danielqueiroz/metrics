package com.metrics.stats.domain;

public class ParameterStats {

    private String machineId;
    private String parameterName;
    private Double average;
    private Double median;
    private Long min;
    private Long max;

    private ParameterStats(String machineId, String parameterName, Double average,
                          Double median, Long min, Long max) {
        this.machineId = machineId;
        this.parameterName = parameterName;
        this.average = average;
        this.median = median;
        this.min = min;
        this.max = max;
    }

    public String getMachineId() {
        return machineId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public Double getAverage() {
        return average;
    }

    public Double getMedian() {
        return median;
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }

    public static class Builder {
        private String machineId;
        private String parameterName;
        private Double average;
        private Double median;
        private Long min;
        private Long max;

        public Builder setMachineId(String machineId) {
            this.machineId = machineId;
            return this;
        }

        public Builder setParameterName(String parameterName) {
            this.parameterName = parameterName;
            return this;
        }

        public Builder setAverage(Double average) {
            this.average = average;
            return this;
        }

        public Builder setMedian(Double median) {
            this.median = median;
            return this;
        }

        public Builder setMin(Long min) {
            this.min = min;
            return this;
        }

        public Builder setMax(Long max) {
            this.max = max;
            return this;
        }

        public ParameterStats build() {
            return new ParameterStats(machineId, parameterName, average, median, min, max);
        }
    }
}
