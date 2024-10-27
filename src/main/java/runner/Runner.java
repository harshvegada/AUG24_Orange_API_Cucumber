package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featureFiles",
        glue = "steps",
        tags = "@employeeCURD or @employeeCURD1 or @employeeCURD2",
        monochrome = true,
        publish = true,
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","rerun:target/rerun.txt"},
        dryRun = false
)
public class Runner {
}
