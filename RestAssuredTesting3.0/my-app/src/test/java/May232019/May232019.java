package May232019;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class May232019 {
    public static void main(String args[]){
//            System.out.println("Hello World");
            RestAssured.baseURI="https://maps.googleapis.com";
            given().
                    param("location","-33.8670522,151.1957362").
                    param("radius","1500").
                    param("key","AIzaSyDjC9yTsu_GIwgjhqN-kLy4fLqMCnNCREo").
            when().
                    get("/maps/api/place/nearbysearch/json").
            then().
                    assertThat().
                    statusCode(200).and().
                    contentType(ContentType.JSON).body("results[0].geometry.location.lat",equalTo("-33.8585683"));
    }
}
