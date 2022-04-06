import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


public class CheckPetStore {

    String uri = "https://petstore.swagger.io";

/*
    1 - pet store un boş oldugunun kontrol edilmesi
    Bu method petId' 975 olan pet var olmadığı için 404 hatası vermekte
*/
    @Test
    void isPetStoreEmpty() {

        RestAssured
                .given()
                .baseUri(uri)
                .basePath("/v2/pet/{petId}")
                .pathParam("petId", "975")
                .header("accept", "application/json")
                .get()
                .then()
                .statusCode(200);
    }
}
