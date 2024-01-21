package stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestBuildData;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification request;
	Response response;
	TestBuildData testBuildData = new TestBuildData();
	static String placeID;
	ResponseSpecification resspec;
	/*
	 * @Given("Add Place Payload") public void add_place_payload() throws
	 * FileNotFoundException, IOException { request =
	 * given().spec(requestSpecification()).body(testBuildData.addPlacePayLoad()); }
	 */

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address)
			throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).body(testBuildData.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String method) throws FileNotFoundException, IOException {
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		// constructor will be called with value of resource which you pass

		APIResources resourceapi = APIResources.valueOf(resource);
		System.out.println(resourceapi.getResource());

		if (method.equalsIgnoreCase("POST")) {
			response = request.when().post(resourceapi.getResource()).then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("GET")) {
			response = request.when().get(resourceapi.getResource()).then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = request.when().delete(resourceapi.getResource()).then().spec(resspec).extract().response();
		}
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
		assertEquals(parseToJson(response).getString(string), string2);
	}

	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource)
			throws FileNotFoundException, IOException {
		placeID = parseToJson(response).getString("place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_with_http_request(resource, "GET");
		assertEquals(expectedName, parseToJson(response).getString("name"));
	}

	@Given("Delete Place Payload with placeID")
	public void delete_place_payload_with_place_id() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).body(testBuildData.deletePlacePayLoad(placeID));
	}

}
