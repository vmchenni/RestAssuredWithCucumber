package May232019;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUsers {
    @Test
    public  void Test1(){

        RestAssured.baseURI="https://reqres.in/";
        given().
                param("page","2").
        when().
                get("api/users").
        then().
                assertThat().statusCode(200).
                body("data[0].first_name",equalTo("Eve")).and().
                contentType(ContentType.JSON).and().header("server","cloudflare");
    }
}
