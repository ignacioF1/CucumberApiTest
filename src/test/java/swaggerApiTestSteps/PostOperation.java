package swaggerApiTestSteps;

import static constants.Constants.JSON_POST;
import static constants.Constants.JSON_POST_RESPONSE;
import static constants.Constants.URL_POST;

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

public class PostOperation{

    private int responseStatus;
    private String responseBody;
    private String petId;
    private String petName;

    // Scenario 1
    @Given("a new available pet called {string} with a random id")
    public void a_new_pet_with_name_and_random_id(String petName) throws IOException {
        this.petName = petName;
        TestPet.testPetName = petName;
        int min = 1;
        int max = 65000;
        // For petId use a random number between 1 and 65000
        this.petId = String.valueOf((int) (Math.random() * (max - min + 1) + min));
        TestPet.testPetId = this.petId;
    }

    @When("doing a POST operation in Swagger´s Pet Store API, adding the new pet")
    public void do_post_adding_new_pet() throws IOException {
        URL url = new URL(URL_POST);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        String jsonInputString = String.format(JSON_POST,petId,petName);
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

    @Then("assert the response contains the added pet and a {int} is received")
    public void assertTheNewPetWasCorrectlyAddedToTheStore(int expectedResponseStatus) {
        String expectedResponseBody = String.format(JSON_POST_RESPONSE,petId,petName);
        Assert.assertEquals(responseBody, expectedResponseBody);
        Assert.assertEquals(responseStatus, expectedResponseStatus);
    }

/*    // Scenario 2
    @Given("the INVALID id {string}")
    public void anInvalidInputForTheId(String invalidId) {
        this.petId = invalidId;
        this.petName = "TestPet123";
    }

    @When("doing a POST operation in Swagger´s Pet Store API with a new pet")
    public void doingAPOSTOperationInSwaggerSPetStoreAPIWithANewPet() throws IOException {
        do_post_adding_new_pet();
    }

    @Then("assert the response status is {int}")
    public void assertThatTheResponseStatusIs(int expectedResponseStatus) {
        Assert.assertEquals(responseStatus, expectedResponseStatus);
    }*/

}
