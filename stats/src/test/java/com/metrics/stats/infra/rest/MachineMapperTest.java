package com.metrics.stats.infra.rest;

import com.metrics.stats.domain.Machine;
import com.metrics.stats.domain.exception.RequiredValueException;
import com.metrics.stats.infra.rest.dto.MachineDTO;
import com.metrics.stats.infra.rest.dto.mapper.MachineMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MachineMapperTest {

    @Test
    public void test_domain_to_dto() throws RequiredValueException {
        Machine machine = new Machine("key", "name");
        MachineDTO machineDTO = MachineMapper.INSTANCE.toDTO(machine);
        Assert.assertEquals(machine.getId(), machineDTO.getKey());
        Assert.assertEquals(machine.getName(), machineDTO.getName());
    }
}
