package com.metrics.stats.domain.service;

import com.metrics.stats.domain.Parameter;

import java.util.List;

public interface MachineParameterService {
    void insert(List<Parameter> machineList);
}
