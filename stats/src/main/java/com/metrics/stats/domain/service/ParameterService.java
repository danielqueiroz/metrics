package com.metrics.stats.domain.service;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.ParameterStats;

import java.time.LocalDateTime;
import java.util.List;

public interface ParameterService {
    void insert(List<Parameter> machineList);
    List<Parameter> findLatestParametersByMachineId(String machineId);
    List<ParameterStats> findComputedParameterInfo(String machineId, String parameterName, LocalDateTime from);
}
