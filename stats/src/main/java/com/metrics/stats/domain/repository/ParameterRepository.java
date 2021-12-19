package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.Parameter;

import java.util.List;

public interface ParameterRepository {
    void insert(List<Parameter> parameterList);
    List<Parameter> findLatestParametersByMachineId(String machineId);
}
