package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class Basic {
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/SpringBootRestApi/api/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    protected String createResource(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().header("location");
    }

    protected void putResource(String path, Object bodyPayload) {
               given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .put(path)
                .then()
                .statusCode(200);
    }

    protected static boolean deleteByIdResource(String locationHeader) {
        try{
            given()
             .spec(spec)
             .when()
             .delete(locationHeader)
             .then()
             .statusCode(204);
            System.out.println("Пользователь удалён");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e);
            return false;
        }
    }

    protected static boolean deleteAllResources(String locationHeader) {
        try{
            given()
                    .spec(spec)
                    .when()
                    .delete(locationHeader)
                    .then()
                    .statusCode(204);
            System.out.println("Все пользователи удалены");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя: " + e);
            return false;
        }
    }

    protected static <T> T getResource(String locationHeader, Class<T> responseClass) {
        return given()
                .spec(spec)
                .when()
                .get(locationHeader)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }
}
