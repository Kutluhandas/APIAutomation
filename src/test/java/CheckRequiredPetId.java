import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class CheckRequiredPetId {

    String uri = "https://petstore.swagger.io";

/*
    4- petId ye gore sorgulama yapildiginda pet id alaninin required oldugunun kontrol edilmesi
    Bu method petId parametresi verilmediği için 405 hatası vermekte
*/
    @Test
    void isPetStoreEmpty() {

        RestAssured
                .given()
                .baseUri(uri)
                .basePath("/v2/pet/{petId}")
                .pathParam("petId", "")
                .header("accept", "application/json")
                .get()
                .then()
                .statusCode(200);
    }
}
