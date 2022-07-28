package com.rss.client.stockdatafetcher;


import com.rss.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class StockDataFetcherClient {
    private final RestTemplate restTemplate;
    public void sendAll(List<Stock> stocks) {
        log.info("Sending request to StockDataFetcher stocks:");

        final String URL = "http://localhost:8080/api/v1/stocks";
        String responseBody = restTemplate.postForEntity(URL, stocks, String.class).getBody();
        log.info(responseBody);
    }
}
