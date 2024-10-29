package com.stock.portfolio.service;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.portfolio.domain.Stock;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RTStockPriceServiceE2ETest {

 @BeforeAll
 public static void setup() {
     RestAssured.baseURI = "http://localhost";
     RestAssured.port = 8080; // Ensure your application runs on this port during tests
 }

 @Test
 public void testGetRealTimePriceE2E() {
     String symbol = "AAPL";

     given()
         .queryParam("symbol", symbol)
     .when()
         .get("/api/price")
     .then()
         .statusCode(200)
         .contentType("application/json")
         .body("price", is(notNullValue())); // Expect that the response body contains a price field
 }
}
