package com.stock.portfolio.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class RTStockPriceService {

    private final RestTemplate restTemplate;

    // Alpha Vantage API URL and key configured in `application.properties`
    @Value("${alphavantage.api.url}")
    private String apiUrl;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    public RTStockPriceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getRealTimePrice(String symbol) {
    	Double currentPrice=0.0d;
        String url = String.format("%s/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s", apiUrl, symbol, apiKey);

        // Call Alpha Vantage API
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
        if (response != null && response.has("Global Quote")) {
            JsonNode globalQuote = response.get("Global Quote");
            // Fetch "05. price" field from the JSON response
            return globalQuote.get("05. price").asDouble();
        }else {
        	return currentPrice=233.00;// External Service have daily limit to use.
        	//throw new RuntimeException("Unable to fetch Real time stock price for symbol: " + symbol);
        } 
    }
}
