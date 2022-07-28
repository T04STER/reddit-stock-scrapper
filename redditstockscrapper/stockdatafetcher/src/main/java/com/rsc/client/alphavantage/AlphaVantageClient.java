package com.rsc.client.alphavantage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsc.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@AllArgsConstructor
@Slf4j
public class AlphaVantageClient {
    private final RestTemplate restTemplate;
    private final String API_KEY = "key";
    private final String API_URL = "https://alpha-vantage.p.rapidapi.com";

    public Stock getStockBySymbol(String symbol) {
        String url = API_URL +
                "/query?function=GLOBAL_QUOTE&symbol=" +
                symbol +
                "&datatype=json";

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", API_KEY);
        headers.add("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com");

        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        try {
            return new ObjectMapper().readerFor(Stock.class).readValue(response.getBody());
        } catch (JsonProcessingException e) {
            log.warn("Did not find match for {} symbol", symbol);
            return null;
        }

    }
}
