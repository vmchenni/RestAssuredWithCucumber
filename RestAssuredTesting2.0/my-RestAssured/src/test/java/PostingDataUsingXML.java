import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.io.File.*;

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

public class PostingDataUsingXML {

    @Test
    public void fnPostdataUsingXML() throws IOException {
        String sXMLData=GenerateResourceInStringFormat("src/test/Resource/PostResource.xml");
        RestAssured.baseURI="http://216.10.245.166";
        given()
        .body(sXMLData)
        .when()
        .post("/maps/api/place/add/xml?key= qaclick123")
        .then()
        .statusCode(200);

    }

    public static String GenerateResourceInStringFormat(String sFilePath) throws IOException {

        File file=new File(sFilePath);
        FileInputStream fis =new FileInputStream(file);
        return new String(String.valueOf(fis.read()));
    }
}
