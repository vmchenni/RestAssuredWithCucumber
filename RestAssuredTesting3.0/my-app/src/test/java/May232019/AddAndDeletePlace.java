package May232019;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class AddAndDeletePlace {
    @Test
    public void addanddelete(){

        //Create place
        String sBody="{\n" +
                "\n" +
                "    \"location\":{\n" +
                "\n" +
                "        \"lat\" : -38.383494,\n" +
                "\n" +
                "        \"lng\" : 33.427362\n" +
                "\n" +
                "    },\n" +
                "\n" +
                "    \"accuracy\":50,\n" +
                "\n" +
                "    \"name\":\"Frontline house\",\n" +
                "\n" +
                "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                "\n" +
                "    \"address\" : \"29, side layout, cohen 09\",\n" +
                "\n" +
                "    \"types\": [\"shoe park\",\"shop\"],\n" +
                "\n" +
                "    \"website\" : \"http://google.com\",\n" +
                "\n" +
                "    \"language\" : \"French-IN\"\n" +
                "\n" +
                "}";
//Add place and Extract to response
        RestAssured.baseURI="http://216.10.245.166";
        Response res=given().queryParam("key"," qaclick123").body(sBody).
        when().post("/maps/api/place/add/json").
        then().assertThat().statusCode(200).extract().response();

        String responseString=res.asString();
        System.out.println("Raw data is :-"+responseString);

//Converting String to JSON Path
        JsonPath js=new JsonPath(responseString);

// Extract the data
        String sPlaceID=js.get("place_id");
        System.out.println("Place Id is :"+sPlaceID);

//Capture place id and pass it to delete
        String sPlaceIDForDelete="{\n" +
                "    \"place_id\":\""+sPlaceID+"\"\n" +
                "}";
        given().queryParam("key"," qaclick123").body(sPlaceIDForDelete).
        when().post("/maps/api/place/delete/json").
        then().assertThat().statusCode(200).and().body("status",equalTo("OK"));

    }
}
