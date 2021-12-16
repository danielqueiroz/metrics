package com.metrics.stats;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        extraGlue = {"com.metrics.stats.config","com.metrics.stats.steps"})
public class CucumberIntegrationIT {
}
