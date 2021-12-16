package com.metrics.stats.infra.service;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.EntityNotFoundException;
import com.metrics.stats.domain.repository.MachineRepository;
import com.metrics.stats.domain.service.MachineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public void insert(List<Machine> machineList) {
        machineRepository.insert(machineList);
    }

    @Override
    public Machine findById(String machineId) {
        Optional<Machine> optionalMachine = machineRepository.findById(machineId);
        return optionalMachine.orElseThrow(EntityNotFoundException::new);
    }
}
