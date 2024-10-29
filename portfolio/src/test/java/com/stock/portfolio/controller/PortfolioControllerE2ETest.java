package com.stock.portfolio.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PortfolioControllerE2ETest {

 @BeforeAll
 public static void setup() {
     RestAssured.baseURI = "http://localhost";
     RestAssured.port = 8080;
 }

 @Test
 public void testGetTotalPortfolioValue_Success() {
     given()
         .contentType(ContentType.JSON)
     .when()
         .get("/api/portfolio/totalValue")
     .then()
         .statusCode(200)
         .contentType(ContentType.JSON)
         .body(greaterThan(0.0));
 }

 @Test
 public void testGetTotalPortfolioValue_Failure() {
     given()
         .contentType(ContentType.JSON)
     .when()
         .get("/api/portfolio/totalValue")
     .then()
         .statusCode(500)
         .contentType(ContentType.JSON)
         .body(containsString("Failed to Fetch stock"));
 }
}
