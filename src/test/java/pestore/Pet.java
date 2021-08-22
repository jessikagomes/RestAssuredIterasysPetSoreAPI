package pestore;

import static org.hamcrest.CoreMatchers.is;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson (String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 1)
    public void incluirPet () throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("available"))
                .body("id", is(notNullValue()))
                .body("tags.name", contains("sta"))
        ;

    }

    @Test(priority = 2)
    public void consultarPet(){

        String petId = "9223372036854775807";

        given()
                .contentType("application/jsom")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("status", is("available"))
                .body("id", is(notNullValue()))
                .body("tags.name", contains("sta"))
        ;

    }
}
