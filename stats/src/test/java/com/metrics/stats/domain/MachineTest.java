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
        Machine machine = new Machine("key", "name");
        Assert.assertEquals("key", machine.getId());
        Assert.assertEquals("name", machine.getName());
    }

    @Test(expected = RequiredValueException.class)
    public void test_missing_id() throws RequiredValueException {
        new Machine(null, "name");
    }

    @Test(expected = RequiredValueException.class)
    public void test_missing_name() throws RequiredValueException {
        new Machine("id", null);
    }

    @Test(expected = RequiredValueException.class)
    public void test_empty_id() throws RequiredValueException {
        new Machine("   ", "name");
    }

    @Test(expected = RequiredValueException.class)
    public void test_empty_name() throws RequiredValueException {
        new Machine("id", "   ");
    }
}
