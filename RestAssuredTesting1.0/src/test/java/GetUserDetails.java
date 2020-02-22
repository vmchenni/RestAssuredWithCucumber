import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class GetUserDetails {

   @Test
   public void fnTest(){

        RestAssured.baseURI="https://reqres.in";
        given().
        when().
                get("/api/users?page=2").
        then().
                assertThat().statusCode(200).
        and().body("data[0].first_name",equalToIgnoringCase("Eve"));




    }
}

