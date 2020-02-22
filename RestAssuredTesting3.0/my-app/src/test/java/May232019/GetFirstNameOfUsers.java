package May232019;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import sun.misc.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetFirstNameOfUsers {
    @Test
    public  void Test1(){

        RestAssured.baseURI="https://reqres.in/";
        Response resp=given().log().all().
                            param("page","2")
                .when().
                            get("api/users").
                then().log().all().
                        statusCode(200).extract().response();
        String responseAsString=resp.asString();
        JsonPath jsonPath=new JsonPath(responseAsString);
        String sFirstname=jsonPath.get("data[0].first_name");
        System.out.println("First name is:-"+sFirstname);
        int iSize=jsonPath.get("data.size()");
        for(int iStart=0;iStart<iSize;iStart++){
            System.out.println(jsonPath.get("data["+iStart+"].first_name"));
        }

    }
}
