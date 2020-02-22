package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = {"./Features/UAT 1"},
        format = { "pretty", "json:target/cucumber-CP-418.json" },
        tags = {"@CP-418"},
        glue = {"stepDefinition"})

public class CP_418_UAT1_TestRunner {
//	./Features/UAT 3
//	./Features/PEAK/Completed
//	./Features/PEAK
}
