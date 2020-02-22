import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class GetResponseAndPassItToNextRequest {
    @Test
    public void AddPalceAndDeleteIt(){
        RestAssured.baseURI="http://216.10.245.166";
        Response resp=given().body("{\n" +
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
                "}\n").
                when().
                post("/maps/api/place/add/json?key= qaclick123").
                then().
                statusCode(200).
                and().
                body("status",equalTo("OK")).
                extract().response();

        //Collect response in string.
        String respString=resp.asString();
        //converting to JSON path
        JsonPath js=new JsonPath(respString);
        String sPlaceId=js.get("place_id");
        System.out.println("Place ID is :-"+sPlaceId);

        //Passing it to next request
        given().
                body("{\n" +
                        "    \"place_id\":\""+sPlaceId+"\"\n" +
                        "}\n").
                when().post("/maps/api/place/delete/json?key=qaclick123")
                .then()
                .statusCode(200)
                .and()
                .body("status",equalTo("OK"));



    }
}
