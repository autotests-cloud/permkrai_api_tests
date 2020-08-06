package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculateTests {
    @BeforeAll
    static void beforeEach() {
        RestAssured.baseURI = "https://asn.permkrai.ru";
    }

    @Test
    void someCalculateTest() {
        String data = "{\"typeOfVehicle\":{\"id\":1,\"label\":\"Автомобили легковые\",\"type\":\"Мощность двигателя, л.с.\"},\"years\":{\"id\":2019,\"label\":\"2019\"},\"month\":{\"id\":12,\"label\":\"12 месяцев\"},\"privileges\":{\"id\":3,\"label\":\"Налогоплательщики, достигшие возраста, при котором возникает право на пенсию по старости или в случае достижения возраста женщинами - 55 лет, мужчинами - 60 лет\",\"yearId\":2019},\"carYearOfIssue\":\"3\",\"taxBase\":\"1\",\"markCar\":null,\"modelCar\":null,\"isCarFlag\":false}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/transport-tax/api/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String totalTax = response.path("totalTax");
        assertEquals(totalTax, "10", response.asString());
    }
}
