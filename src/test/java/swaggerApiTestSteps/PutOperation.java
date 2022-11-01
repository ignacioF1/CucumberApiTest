package swaggerApiTestSteps;

import static constants.Constants.JSON_PUT;
import static constants.Constants.JSON_PUT_RESPONSE;
import static constants.Constants.URL_PUT;

import auxiliary.TestPet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.testng.Assert;

public class PutOperation {

    private int responseStatus;
    private String responseBody;
    private String petId;
    private String petName;
    private String status;

    @Given("the pet added in the previous test")
    public void load_previous_test_pet() {
        this.petId = TestPet.testPetId;
        this.petName = TestPet.testPetName;
    }

    @When("doing a PUT operation in Swagger´s Pet Store API, updating its status to {string}")
    public void doingAPUTOperationInSwaggerSPetStoreAPIUpdatingItsStatusTo(String petStatus) throws IOException {
        this.status = petStatus;
        URL url = new URL(URL_PUT);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        String jsonInputString = String.format(JSON_PUT,petId,petName,status);
        DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
        out.writeBytes(jsonInputString);
        out.flush();
        out.close();
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
            responseBody = stringBuilder.toString();
        }
        
    }

    @Then("assert the pet status was updated correctly and {int} is received")
    public void assertThePetStatusWasUpdatedCorrectlyAndIsReceived(int expectedResponseStatus) {
        String expectedResponseBody = String.format(JSON_PUT_RESPONSE,petId,petName,status);
        Assert.assertEquals(responseBody, expectedResponseBody);
        Assert.assertEquals(responseStatus, expectedResponseStatus);
    }
}
