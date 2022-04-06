import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.path.json.JsonPath.from;

/*
    5- Yeni bir pet oluşturularak, daha sonrasında oluşturulan bu pet in id ye gore listelenip kontrol edilmesi
    İlk method'da post isteğiyle pet oluşturuldu. İkinci method'da oluşturulan pet'in id'si de kullanılarak
    get isteğiyle çağırıldı.
*/

public class CreateNewPetAndCheck {

    public static String uri = "https://petstore.swagger.io";
    public static String petId;

    @Test
    void addPetAndCheckById() {

        File postBody = new File(getClass().getClassLoader().getResource("petPost.json").getFile());

        Response response = RestAssured
                .given()
                .baseUri(uri)
                .basePath("/v2/pet")
                .header("accept", "application/json")
                .contentType(ContentType.JSON)
                .body(postBody)
                .post();
        response
                .then()
                .statusCode(200);

        petId = from(response.getBody().asString()).getString("id");
    }

    @Test
    void getByPetId() {

        Response res =RestAssured
                .given()
                .baseUri(uri)
                .basePath("/v2/pet/{petId}")
                .pathParam("petId", petId)
                .header("accept", "application/json")
                .get();
        res
                .getBody().prettyPrint();
        res
                .then()
                .statusCode(200);
    }


}
