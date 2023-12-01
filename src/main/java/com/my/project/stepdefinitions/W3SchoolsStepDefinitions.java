package com.my.project.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class W3SchoolsStepDefinitions {

    private RequestSpecification requestSpecification;
    private Response response;

    private static final Logger LOG = LoggerFactory.getLogger(W3SchoolsStepDefinitions.class);

    public W3SchoolsStepDefinitions() {
    }

    ///GET
    @Given("The following GET API endpoint {string}")
    public void theFollowingGETAPIEndpointOne(String url) {
        requestSpecification = given().baseUri(url);
        LOG.info(format("User has accessed the following page - {%s}", url));
    }

    @When("GET request is sent")
    public void getRequestIsSent() {
        response = requestSpecification.get();
        LOG.info("User sends a get request!");
    }

    @Then("the response Status {int} displayed")
    public void theResponseStatusDisplayed(int status) {
        int actualStatus = response.getStatusCode();
        assertThat(status, is(actualStatus));
        LOG.info(format("User has received status - {%d}", status));
    }

    @And("response body contains the following text$")
    public void responseBodyContainsTheFollowingTextEnUSLtr(String expectedResponseBody) {
        final String actualResponseBody = response.getBody().asString();
        assertThat(actualResponseBody, containsString(expectedResponseBody));
        LOG.info("Expected response body matches with actual response body!");
    }

    ///POST
    @Given("The following POST API endpoint {string}")
    public void theFollowingPOSTAPIEndpointOne(String url) {
        requestSpecification = given()
                .baseUri(url)
                .contentType(ContentType.MULTIPART)
                .multiPart("code", "public class Main {\n" +
                        "  public static void main(String[] args) {\n" +
                        "    System.out.println(\"Hello World\");\n" +
                        "  }\n" +
                        "}\n");
        LOG.info(format("User has accessed the following page - {%s}", url));
    }

    @When("POST request is sent with the following body")
    public void postRequestIsSentWithTheFollowingBody() {
        response = requestSpecification.post();
        LOG.info("User sends a post request!");
    }
}
