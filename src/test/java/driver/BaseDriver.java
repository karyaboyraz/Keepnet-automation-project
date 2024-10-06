package driver;

import Methods.Faker;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;

import java.io.IOException;

public class BaseDriver {

    @BeforeSuite
    public void faker() throws IOException {
        Faker generator = new Faker();
        generator.generateUserCSV();
    }

    @BeforeScenario
    public void setUp() {
        DriverFactory.initialize();
    }

    @AfterScenario
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}