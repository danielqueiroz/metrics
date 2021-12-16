package com.metrics.stats.domain;

import com.metrics.stats.domain.exception.RequiredValueException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
public class MachineTest {

    @Test
    public void test_valid_machine_domain() throws RequiredValueException {
        Machine machine = new Machine("key", "name", null);
        Assert.assertEquals("key", machine.getId());
        Assert.assertEquals("name", machine.getName());
        Assert.assertNotNull(machine.getLocalDateTime());
    }

    @Test
    public void test_valid_machine_domain_with_local_date_time() throws RequiredValueException {
        LocalDateTime localDateTime = LocalDateTime.now();
        Machine machine = new Machine("key", "name", localDateTime);
        Assert.assertEquals("key", machine.getId());
        Assert.assertEquals("name", machine.getName());
        Assert.assertEquals(localDateTime, machine.getLocalDateTime());
    }

    @Test(expected = RequiredValueException.class)
    public void test_missing_id() throws RequiredValueException {
        new Machine(null, "name", LocalDateTime.now());
    }

    @Test(expected = RequiredValueException.class)
    public void test_missing_name() throws RequiredValueException {
        new Machine("id", null, null);
    }

    @Test(expected = RequiredValueException.class)
    public void test_empty_id() throws RequiredValueException {
        new Machine("   ", "name", null);
    }

    @Test(expected = RequiredValueException.class)
    public void test_empty_name() throws RequiredValueException {
        new Machine("id", "   ", null);
    }
}
