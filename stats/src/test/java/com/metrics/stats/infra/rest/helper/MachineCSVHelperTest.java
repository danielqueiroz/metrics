package com.metrics.stats.infra.rest.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MachineCSVHelperTest {

    @Test
    public void isCSVFile() {

        MockMultipartFile csvFile =
                new MockMultipartFile("machine", "machines.csv",
                        "text/csv", "key, value".getBytes());

        Assert.assertTrue(MachineCSVHelper.isCSVFile(csvFile));

    }
}
