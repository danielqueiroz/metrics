package com.metrics.stats.infra.service;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.ParameterStats;
import com.metrics.stats.domain.repository.ParameterRepository;
import com.metrics.stats.domain.repository.ParameterStatsRepository;
import com.metrics.stats.domain.service.ParameterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private ParameterRepository parameterRepository;
    private ParameterStatsRepository parameterStatsRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository, ParameterStatsRepository parameterStatsRepository) {
        this.parameterRepository = parameterRepository;
        this.parameterStatsRepository = parameterStatsRepository;
    }

    @Override
    public void insert(List<Parameter> machineList) {
        parameterRepository.insert(machineList);
    }

    @Override
    public List<Parameter> findLatestParametersByMachineId(String machineId) {
        return parameterRepository.findLatestParametersByMachineId(machineId);
    }

    public List<ParameterStats> findComputedParameterInfo(String machineId, String parameterName, LocalDateTime from) {
        return parameterStatsRepository.findParameterStats(machineId, parameterName, from);
    }
}
