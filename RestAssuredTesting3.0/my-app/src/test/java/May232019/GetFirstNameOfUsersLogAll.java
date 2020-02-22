package May232019;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetFirstNameOfUsersLogAll {
    @Test
    public  void Test1(){

        RestAssured.baseURI="https://reqres.in/";
        Response res=given().log().all().
                param("page","2").
                when().
                        get("api/users").
                then().
                        assertThat().
                        statusCode(200).extract().response();
        String sResponseString=res.asString();
//        System.out.println("Response is :-"+sResponseString);
        JsonPath js= new JsonPath(sResponseString);
        int iCount=js.get("data.size()");
        System.out.println("Array size is:"+iCount);
        for(int iStart=0;iStart<iCount;iStart++){
            System.out.println("Firstname is:-"+js.get("data["+iStart+"].first_name"));
        }




    }
}
