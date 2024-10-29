package com.stock.portfolio.service;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.portfolio.domain.Stock;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PortfolioServiceE2ETest {

 @BeforeAll
 public static void setup() {
     RestAssured.baseURI = "http://localhost";
     RestAssured.port = 8080; // Ensure your application runs on this port during tests
 }

 @Test
 public void testCalculateTotalPortfolioValue() {
     // First, add some stocks to the portfolio
     Stock stock1 = new Stock("AAPL", 10);
     Stock stock2 = new Stock("GOOGL", 5);

     given()
         .contentType(ContentType.JSON)
         .body(stock1)
     .when()
         .post("/api/addStock")
     .then()
         .statusCode(200);

     given()
         .contentType(ContentType.JSON)
         .body(stock2)
     .when()
         .post("/api/addStock")
     .then()
         .statusCode(200);

     // Now, get the total portfolio value
     when()
         .get("/api/portfolio/totalValue")
     .then()
         .statusCode(200)
         .contentType(ContentType.JSON)
         .body(is(notNullValue())); // Validate that we get a non-null response
 }
}
