package com.stock.portfolio.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.stock.portfolio.domain.HistoricalStock;
import com.stock.portfolio.repository.HistoricalStockRepo; // Make sure to include the appropriate repo

@SpringBootTest
@AutoConfigureMockMvc
public class HistoricalStockControllerE2ETest {

    @Autowired
    private HistoricalStockRepo historicalStockRepo;

    @BeforeEach
    public void setup() {
        // Clean the repository before each test to avoid interference
        historicalStockRepo.deleteAll();
        
        // Seed test data
        HistoricalStock stock1 = new HistoricalStock("AAPL", 150.0, LocalDate.now());
        HistoricalStock stock2 = new HistoricalStock("GOOGL", 2800.0, LocalDate.now());
        historicalStockRepo.saveAll(Arrays.asList(stock1, stock2));
    }

    @Test
    public void getHistoricalData_ShouldReturnListOfHistoricalStocks() {
        // Perform the GET request and validate the response
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/stocks/getAllHistoricalStocks")
            .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("$", hasSize(2))
            .body("[0].symbol", equalTo("AAPL"))
            .body("[1].symbol", equalTo("GOOGL"));
    }

    @Test
    public void getHistoricalData_ShouldReturnNotFound_WhenNoData() {
        // Clean the repository
        historicalStockRepo.deleteAll();
        
        // Perform the GET request and validate the response
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/stocks/getAllHistoricalStocks")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void getHistoricalData_ShouldReturnInternalServerError_OnException() {
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/stocks/getAllHistoricalStocks")
            .then()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
