package com.metrics.stats.domain.service;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.EntityNotFoundException;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.domain.repository.MachineRepository;
import com.metrics.stats.infra.service.MachineServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class MachineServiceTest {

    @Test(expected = EntityNotFoundException.class)
    public void test_will_throw_exception_when_search_by_unknown_id() {
        MachineRepository machineRepository = mock(MachineRepository.class);
        when(machineRepository.findById(anyString())).thenReturn(Optional.empty());
        MachineService machineService = new MachineServiceImpl(machineRepository);
        machineService.findById("key");
    }

    @Test
    public void test_return_entity_by_id() throws RequiredValueException {
        Machine machine = new Machine("key", "name", null);
        MachineRepository machineRepository = mock(MachineRepository.class);
        when(machineRepository.findById("key")).thenReturn(Optional.of(machine));
        MachineService machineService = new MachineServiceImpl(machineRepository);
        Machine returnedMachine = machineService.findById("key");

        Assert.assertEquals(machine.getId(), returnedMachine.getId());
        Assert.assertEquals(machine.getName(), returnedMachine.getName());
    }

}
