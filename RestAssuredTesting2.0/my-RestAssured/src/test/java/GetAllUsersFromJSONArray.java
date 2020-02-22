import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllUsersFromJSONArray {
    @Test
    public void fnGetAllUsersFromJSONArray(){

        RestAssured.baseURI="https://reqres.in";
        Response resp=  given().log().all()
                        .when().get("/api/users?page=2")
                        .then().statusCode(200)
                        .and().log().all()
                        .extract().response();
        String sResponseString=resp.asString();
        JsonPath jsonpath=new JsonPath(sResponseString);
        int iItemsCount=jsonpath.get("data.size()");
        System.out.println("Size of array is:-"+iItemsCount);
        for(int i=0;i<iItemsCount;i++){
            System.out.print("First Name is :- "+jsonpath.get("data["+i+"].first_name"));
            System.out.println("Last Name is :- "+jsonpath.get("data["+i+"].last_name"));
        }
    }
}
