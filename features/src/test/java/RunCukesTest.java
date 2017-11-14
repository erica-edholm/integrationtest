import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import se.omegapoint.store.glue.SpringConfig;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"}, features = "src/", glue = "se.omegapoint.store.steps")
@ContextConfiguration(classes = SpringConfig.class)
public class RunCukesTest {
}
