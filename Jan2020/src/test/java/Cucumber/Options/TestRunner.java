package Cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\VishwanathChenni\\Project\\UdamyProject\\Jan2020\\src\\test\\java\\features",
        glue = "stepDefinition"
)

public class TestRunner {
}
