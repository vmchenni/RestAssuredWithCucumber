import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostMethod {

    @Test
    public void Postmethod(){

        RestAssured.baseURI="http://216.10.245.166";
        given().
              body("{\n" +
                      "    \"location\":{\n" +
                      "        \"lat\" : -38.383494,\n" +
                      "        \"lng\" : 33.427362\n" +
                      "    },\n" +
                      "    \"accuracy\":50,\n" +
                      "    \"name\":\"Frontline house\",\n" +
                      "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                      "    \"address\" : \"29, side layout, cohen 09\",\n" +
                      "    \"types\": [\"shoe park\",\"shop\"],\n" +
                      "    \"website\" : \"http://google.com\",\n" +
                      "    \"language\" : \"French-IN\"\n" +
                      "}").
        when().post("/maps/api/place/add/json?key= qaclick123").
        then().statusCode(200).and().
        body("status",equalTo("OK")).
        extract().response();


    }
}
