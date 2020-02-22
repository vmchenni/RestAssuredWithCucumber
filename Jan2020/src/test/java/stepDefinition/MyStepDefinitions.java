package stepDefinition;
import cucumber.api.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.Assert;
import pojoclasses.AddPlace;
import pojoclasses.AddPlaceResponsePojo;
import pojoclasses.GetUsers;
import pojoclasses.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MyStepDefinitions {
    String sPayLoad;
    Response response;
    JsonPath js;
    public static AddPlace addPlace;
    AddPlaceResponsePojo addplaceResponse;
    GetUsers getUsers;
    @Given("^Add place payload$")
    public void add_place_payload() throws Throwable {
        addPlace=new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage("French-IN");

        List<String> myList=new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);

        Location location=new Location();
        location.setLat(-38.383498);
        location.setLng(33.427362);
        addPlace.setLocation(location);
    }

    @When("^User calls \"([^\"]*)\" api with post http request$")
    public void user_calls_something_api_with_post_http_request(String strArg1) throws Throwable {
        RestAssured.baseURI="http://216.10.245.166";
                addplaceResponse=given().
                queryParam("key","qaclick123").body(addPlace).
                expect().defaultParser(Parser.JSON).
                when().
                post("/maps/api/place/add/json").
                then().statusCode(200).extract().as(AddPlaceResponsePojo.class);
    }

    @And("^Status in response body is OK$")
    public void status_in_response_body_is_ok() throws Throwable {
        Assert.assertEquals("OK",addplaceResponse.getStatus());
        System.out.println(addplaceResponse.getPlace_id());
    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int arg0) {
//        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Given("How are you")
    public void howAreYou() {
    }

    @Given("Add place payload location lat as {string} , lng as {string}, accuracy as {string}, name as {string} phone as {string}, address as {string}, types as {string}, website as {string} and language as {string}")
    public void addPlacePayloadLocationLatAsLngAsAccuracyAsNameAsPhoneAsAddressAsTypesAsWebsiteAsAndLanguageAs(String slat, String slng, String saccuracy, String name, String phone, String address, String types, String website, String language) {

        addPlace=new AddPlace();
        addPlace.setAccuracy(Integer.parseInt(saccuracy));
        addPlace.setName(name);
        addPlace.setPhone_number(phone);
        addPlace.setAddress(address);
        addPlace.setWebsite(website);
        addPlace.setLanguage(language);
        String[] myList=types.split("#");
//        List<String> myList=new ArrayList<String>();
//        myList.add("shoe park");
//        myList.add("shop");
        addPlace.setTypes(Arrays.asList(myList));

        Location location=new Location();
        location.setLat(Double.parseDouble(slat));
        location.setLng(Double.parseDouble(slng));
        addPlace.setLocation(location);
    }

    @Given("User calls get user API")
    public void userCallsGetUserAPI() {
        RestAssured.baseURI="https://reqres.in";
        getUsers=given().log().all().
        when().get("/api/users?page=2").
        then().assertThat().statusCode(200).extract().as(GetUsers.class);

    }

    @Then("Verify the first_name as {string}")
    public void verifyTheFirst_nameAs(String arg0) {
        String fName=getUsers.getData().get(1).getFirst_name();
        System.out.println("FName from response is"+fName);
    }
}
