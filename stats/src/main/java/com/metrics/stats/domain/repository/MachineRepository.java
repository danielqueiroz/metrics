package com.metrics.stats.domain.repository;

import com.metrics.stats.domain.Machine;

import java.util.List;
import java.util.Optional;

public interface MachineRepository {

    void insert(List<Machine> machineList);
    Optional<Machine> findById(String id);

    List<Machine> findLatestInsertedParameters();
}
