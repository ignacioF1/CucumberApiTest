package swaggerApiTestSteps;

import io.cucumber.core.gherkin.Feature;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.Arrays;
import java.util.Objects;
import org.junit.Test;
import org.testng.annotations.AfterTest;

public class CommonSteps {

    static Boolean testsPassed = Boolean.TRUE;

    @Given("do this before each test")
    public void doThisBeforeEachTest1() {
    }

    @BeforeStep
    public void doThisBeforeEachTest(Scenario scenario) {
        System.out.println("Executing Test: " + scenario.getSourceTagNames() + " " + scenario.getStatus());
        if (!Objects.equals(scenario.getStatus().toString(), "PASSED")) {
            CommonSteps.testsPassed = Boolean.FALSE;
        }
    }

    @BeforeAll
    public static void testIntro() {
        System.out.println("LAUNCHING TESTS ...");
    }

    @AfterAll
    public static void testSummary() {
        if (testsPassed == Boolean.TRUE) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.out.println("TEST FAILURE");
        }
    }
}
