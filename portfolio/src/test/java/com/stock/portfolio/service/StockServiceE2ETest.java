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
public class StockServiceE2ETest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testSaveStock() {
        Stock stock = new Stock("AAPL", 10);

        given()
            .contentType(ContentType.JSON)
            .body(stock)
        .when()
            .post("/api/addStock")
        .then()
            .statusCode(200)
            .body(equalTo("AAPL Stock Added Successfully"));
    }

    @Test
    public void testGetAllStocks() {
        when()
            .get("/api/stocks/getAllStocks")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("size()", greaterThan(0));
    }

    @Test
    public void testDeleteStockById() {
        given()
            .pathParam("stockId", 1)
        .when()
            .delete("/api/stock/{stockId}")
        .then()
            .statusCode(200);
    }
}
