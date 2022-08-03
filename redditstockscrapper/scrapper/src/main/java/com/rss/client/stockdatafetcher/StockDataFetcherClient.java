package com.rss.client.stockdatafetcher;


import com.rss.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class StockDataFetcherClient {
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public void sendAll(List<Stock> stocks) {

        final String URL = "http://localhost:8080/api/v1/stocks";
        retryTemplate.execute(retryContext -> {
            log.info("Sending request to StockDataFetcher stocks [try {}]:", retryContext.getRetryCount()+1);
            return restTemplate.postForEntity(URL, stocks, String.class);
        });
    }
}
