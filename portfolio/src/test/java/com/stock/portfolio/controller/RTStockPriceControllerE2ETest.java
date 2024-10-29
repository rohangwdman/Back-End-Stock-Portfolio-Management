package com.stock.portfolio.controller;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RTStockPriceControllerE2ETest {

 @BeforeAll
 public static void setup() {
     RestAssured.baseURI = "http://localhost";
     RestAssured.port = 8080;
 }

 @Test
 public void testGetStockPrice_Success() {
     given()
         .contentType(ContentType.JSON)
         .queryParam("symbol", "AAPL")
     .when()
         .get("/api/price")
     .then()
         .statusCode(200)
         .contentType(ContentType.JSON)
         .body(greaterThan(0.0));
 }

 @Test
 public void testGetStockPrice_Failure() {
     given()
         .contentType(ContentType.JSON)
         .queryParam("symbol", "INVALID_SYMBOL")
     .when()
         .get("/api/price")
     .then()
         .statusCode(500)
         .contentType(ContentType.JSON)
         .body(containsString("Failed to Fetch stock"));
 }
}
