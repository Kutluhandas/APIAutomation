import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CheckInvalidInput {

    String uri = "https://petstore.swagger.io";

    /*
    -3. Invalid input girildiğinde hata kodunun kontrol edilmesi.
    petPost json dosyasında id kısmı'nın integer olması beklenirken String bir değer girildi.
    String değer girildiği için 500 hatası vermekte
     */

    @Test()
    void invalidInput() {

        File postBody = new File(getClass().getClassLoader().getResource("petPostInvalid.json").getFile());

        RestAssured
                .given()
                .baseUri(uri)
                .basePath("/v2/pet/")
                .header("accept", "application/json")
                .contentType(ContentType.JSON)
                .body(postBody)
                .post()
                .then()
                .statusCode(200);
    }
}
