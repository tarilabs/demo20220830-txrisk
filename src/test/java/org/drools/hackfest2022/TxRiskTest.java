package org.drools.hackfest2022;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TxRiskTest {

    @Test
    public void testScenario1() {
        given()
          .body("{ \"Age\": 21, \"Cardholder Status\": \"STANDARD\", \"Transaction Amount\": 50, \"Incident Count\":0 }")
          .contentType(ContentType.JSON)
          .when()
            .post("/txrisk")
          .then()
            .statusCode(200)
            .body("'Cardholder Risk Rating'", is(2))
            .body("'Transaction Risk Rating'", is(3))
            .body("'Block Automatic Processing'", is(true));
    }

    @Test
    public void testScenario2() {
        given()
          .body("{ \"Age\": 21, \"Cardholder Status\": \"SILVER\", \"Transaction Amount\": 50, \"Incident Count\":0 }")
          .contentType(ContentType.JSON)
          .when()
            .post("/txrisk")
          .then()
            .statusCode(200)
            .body("'Cardholder Risk Rating'", is(1))
            .body("'Transaction Risk Rating'", is(2))
            .body("'Block Automatic Processing'", is(false));
    }
}
