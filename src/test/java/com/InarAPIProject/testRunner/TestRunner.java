package com.InarAPIProject.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/InarAPIProject/stepDefinitions",
        tags = "@Regression",
        plugin ="json:target/jsonReports/cucumber-report.json"

)
public class TestRunner {
}
