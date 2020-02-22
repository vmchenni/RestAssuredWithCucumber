import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetResponseAndPassItToNextRequestFramework {
    Properties prop =new Properties();
    @BeforeTest
    public void  fnBeforeClassMethod() throws IOException {


        FileInputStream fis=new FileInputStream("src/ConfiProperties/Config.Properties");
        prop.load(fis);

    }
    @Test
    public void AddPalceAndDeleteIt(){
        RestAssured.baseURI=prop.getProperty("HOST");
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
