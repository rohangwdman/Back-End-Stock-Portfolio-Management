package com.stock.portfolio.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.portfolio.domain.Stock;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StockControllerE2ETest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testAddStockE2E() {
        Stock stock = new Stock("AAPL", 150.0);

        given()
            .contentType(ContentType.JSON)
            .body(stock)
        .when()
            .post("/api/addStock")
        .then()
            .statusCode(200)
            .body(containsString("AAPL Stock Added Successfully"));
    }

    @Test
    public void testGetAllStocksE2E() {
        when()
            .get("/api/stocks/getAllStocks")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }

    @Test
    public void testGetStockByIdE2E_NotFound() {
        when()
            .get("/api/stock/999")
        .then()
            .statusCode(404);
    }
}

