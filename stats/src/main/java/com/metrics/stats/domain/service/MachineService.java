package com.metrics.stats.domain.service;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.MachineParameter;

import java.util.List;

public interface MachineService {

    void insert(List<Machine> machineList);
    Machine findById(String machineId);
}
