package com.metrics.stats.domain.service;

import com.metrics.stats.domain.Parameter;

import java.util.List;

public interface ParameterService {
    void insert(List<Parameter> machineList);
    List<Parameter> findLatestParametersByMachineId(String machineId);
}
