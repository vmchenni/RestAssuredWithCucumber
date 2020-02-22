import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetResponse {
    public static void main(String args[]){

        RestAssured.baseURI="https://maps.googleapis.com";
        given().

        when().
                get("/maps/api/place/findplacefromtext/json?input=mongolian grill&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=AIzaSyDjC9yTsu_GIwgjhqN-kLy4fLqMCnNCREo").
        then().
                assertThat().statusCode(200).and().body("candidates[0].name",equalTo("Mongolian Grill Kirkland")).
        and().header("Server",equalTo("scaffolding on HTTPServer2"));

    }
}
