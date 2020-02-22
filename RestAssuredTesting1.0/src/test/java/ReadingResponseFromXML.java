import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReadingResponseFromXML {

    @Test
    public void fnReadingResponseFromXML() throws IOException {

        String sFileValue=GenerateResourceInStringFormat("src/test/Resource/PostResource.xml");
        System.out.println("File Bytes Are :-"+sFileValue);
        RestAssured.baseURI="http://216.10.245.166";

        Response resp=  given()
                        .body(sFileValue)
                        .when()
                        .post("/maps/api/place/add/xml?key= qaclick123")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String response=resp.asString();
        System.out.println("Response is :-"+response);
        XmlPath xPath=new XmlPath(response);
        xPath.get("place_id");
        System.out.println("Place Id is :-"+xPath.get("place_id"));
    }

    public static String GenerateResourceInStringFormat(String sFilePath) throws IOException {

      return new String(Files.readAllBytes(Paths.get(sFilePath)));
    }
}
