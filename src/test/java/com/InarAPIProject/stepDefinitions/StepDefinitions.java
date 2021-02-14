package com.InarAPIProject.stepDefinitions;

import com.InarAPIProject.pojo.APIResources;
import com.InarAPIProject.utilities.TestDataBuilder;
import com.InarAPIProject.utilities.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {

    RequestSpecification reqspec;
    TestDataBuilder data = new TestDataBuilder();
    ResponseSpecification resspec;
    Response response;
    static String place_id;


    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload(String name, String language, String address) throws FileNotFoundException {
        reqspec = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
    }

    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws FileNotFoundException {
        reqspec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (method.equalsIgnoreCase("POST"))
            response = reqspec.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = reqspec.when().get(resourceAPI.getResource());
    }

    @Then("the API call got success with status code {int}")
    public void the_API_call_got_succes_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }



    // public class StepDefinitions extends Utils {

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        assertEquals(getJsonPath(response, keyValue), Expectedvalue);
    }



    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException, FileNotFoundException {

        // requestSpec
        place_id = getJsonPath(response, "place_id");
        reqspec = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }
}
