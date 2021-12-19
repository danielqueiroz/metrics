package com.metrics.stats.infra.service;

import com.metrics.stats.domain.Parameter;
import com.metrics.stats.domain.repository.ParameterRepository;
import com.metrics.stats.domain.service.ParameterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    private ParameterRepository repository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.repository = parameterRepository;
    }

    @Override
    public void insert(List<Parameter> machineList) {
        repository.insert(machineList);
    }

    @Override
    public List<Parameter> findLatestParametersByMachineId(String machineId) {
        return repository.findLatestParametersByMachineId(machineId);
    }
}
