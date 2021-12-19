package com.metrics.stats.domain;

public class ParameterStats {

    private String machineId;
    private String parameterName;
    private Double average;
    private Double median;
    private Long min;
    private Long max;
    private Long count;

    private ParameterStats(String machineId, String parameterName, Double average,
                          Double median, Long min, Long max, Long count) {
        this.machineId = machineId;
        this.parameterName = parameterName;
        this.average = average;
        this.median = median;
        this.min = min;
        this.max = max;
        this.count = count;
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

    public Long getCount() {
        return count;
    }

    public static class Builder {
        private String machineId;
        private String parameterName;
        private Double average;
        private Double median;
        private Long min;
        private Long max;
        private Long count;

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

        public Builder setCount(Long count) {
            this.count = count;
            return this;
        }

        public String getMachineId() {
            return this.machineId;
        }

        public String getParameterName() {
            return this.parameterName;
        }

        public Long getCount() {
            return count;
        }

        public ParameterStats build() {
            return new ParameterStats(machineId, parameterName, average, median, min, max, count);
        }
    }
}
