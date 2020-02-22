package restassured;
import static  io.restassured.RestAssured.*;
import static  io.restassured.response.Response.*;
import static  org.hamcrest.Matchers.*;


import org.testng.Assert;
import org.testng.annotations.Test;

public class GetResponse {
    @Test
    public void fnGetResponse(){

       //given().
        //when().
        //then().
        //assert
        given().
        when().
                get("https://reqres.in/api/users?page=2").
        then().
                assertThat().
                statusCode(200).
                and().
                body("data[0].last_name",equalTo("Vishwanath")).
                and().
                body("data[2].first_name",equalTo("Tracey")).
                and().
                body("data[1].first_name",equalTo("Charles")).
                and().
                header("content-type",equalTo("application/json; charset=utf-8")).
                and().
                header("server",equalTo("cloudflare"));

    }
}
