package swaggerApiTestSteps;

import static constants.Constants.JSON_DELETE_RESPONSE;
import static constants.Constants.URL_DELETE;

import auxiliary.TestPet;
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

public class DeleteOperation {

    private int responseStatus;
    private String responseBody;
    private String petId;
    private String petName;
    private String status;

    @Given("the pet added in the POST test")
    public void load_previous_test_pet() {
        this.petId = TestPet.testPetId;
        this.petName = TestPet.testPetName;
    }

    @When("doing a DELETE operation by the pet´s id in Swagger´s Pet Store API,")
    public void doingADELETEOperationByPetIdInSwaggerSPetStoreAPI() throws IOException {
        URL url = new URL(URL_DELETE + petId);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("DELETE");
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

    @Then("assert the pet was deleted correctly and {int} is received")
    public void assertThePetWasDeletedCorrectly(int expectedResponseStatus) {
        String expectedResponseBody = String.format(JSON_DELETE_RESPONSE,expectedResponseStatus,petId);
        Assert.assertEquals(responseStatus, expectedResponseStatus);
        Assert.assertEquals(responseBody, expectedResponseBody);
    }
}
