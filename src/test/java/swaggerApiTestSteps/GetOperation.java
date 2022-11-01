package swaggerApiTestSteps;

import static constants.Constants.URL_GET_BY_STATUS;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.testng.Assert;

public class GetOperation {

    int responseStatus;
    String responseBody;
    String petStatus;

    // Scenario 1
    @Given("{string} pet status")
    public void set_pet_status(String petStatus) {
        this.petStatus = petStatus;
    }

    @When("doing a GET operation in Swagger´s Pet Store API, filtering by the given status")
    public void get_pets_by_status() throws IOException {
        URL url = new URL(URL_GET_BY_STATUS + petStatus);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        DataInputStream input = new DataInputStream(httpURLConnection.getInputStream());
        responseStatus = httpURLConnection.getResponseCode();
        if (responseStatus >= 200 && responseStatus <= 299) { // successful response available codes
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            input.close();
            responseBody = stringBuilder.toString();
        }
    }

    @Then("assert the response status is {int}")
    public void assert_the_result_is_the_expected(int expectedResponseStatus) {
        // According to Swagger Pet Store API, receiving a 200
        // response status corresponds to a successful operation
        Assert.assertEquals(responseStatus, expectedResponseStatus);
        // Although we can also assert the response is not null
        Assert.assertNotNull(responseBody);
    }

/*    // Scenario 2
    @Given("the INVALID pet status {string}")
    public void set_invalid_pet_status(String invPetStatus) {
        this.petStatus = invPetStatus;
    }

    @When("doing a GET operation in Swagger´s Pet Store API, filtering by the invalid status")
    public void get_pets_by_invalid_status() throws IOException {
        get_pets_by_status();
    }

    @Then("assert the response status is {int}")
    public void assertTheResponseStatusIs(int expectedResponseStatus) {
        Assert.assertEquals(responseStatus, expectedResponseStatus);
    }*/
}
