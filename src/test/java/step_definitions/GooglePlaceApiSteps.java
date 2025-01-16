package step_definitions;

import data_generation.APIResources;
import data_generation.GeneratePayloadPojo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static step_definitions.Hooks.dataMap;

public class GooglePlaceApiSteps extends RestUtils {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    GeneratePayloadPojo generatePayloadPojo = new GeneratePayloadPojo();

    @Given("Add place payload with {string} and {string}")
    public void add_place_payload(String website, String language) {
        requestSpecification = given()
                .spec(getRequestSpecification()).body(generatePayloadPojo.generateAddPlaceApiPayload(website, language));
    }
    @When("user calls {string} with {string} Http request")
    public void user_calls_with_post_http_request(String apiEndpoint, String httpMethod) {
        APIResources apiResources = APIResources.valueOf(apiEndpoint);
        Map<String, Runnable> httpAction = setHttpAction(apiResources.getEndpoint());
        Runnable action = httpAction.get(httpMethod);
        if(null!=action){
            action.run();
        }

    }
    @Then("Api call is success with status code {int}")
    public void api_call_is_success_with_status_code(Integer statusCode) {
        assertEquals(statusCode, (Integer) response.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(value, getValueFromResponse(response, key));
    }
    public Map<String, Runnable> setHttpAction(String endpoint){
        Map<String, Runnable> httpAction = new HashMap<>();
        httpAction.put("POST", ()->{
            response = requestSpecification
                    .when()
                    .post(endpoint);
        });
        httpAction.put("GET", ()->{
            response = requestSpecification
                    .when()
                    .get(endpoint);
        });
        httpAction.put("DELETE", ()->{
            response = requestSpecification
                    .when()
                    .delete(endpoint);
        });
        return httpAction;
    }
    @And("extract the placeId created")
    public void extractPlaceID(){
        dataMap.put("placeId", getValueFromResponse(response, "place_id"));
        requestSpecification = given().spec(getRequestSpecification())
                .queryParam("place_id", dataMap.get("placeId"));
    }

    @Given("delete place API payload")
    public void deletePlacePayload(){
        requestSpecification = given().spec(getRequestSpecification())
                .body(generatePayloadPojo.getDeletePlaceApiPayload(dataMap.get("placeId").toString()));
    }

}
