package com.rsc;

import com.rsc.client.alphavantage.AlphaVantageClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockDataFetcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockDataFetcherApplication.class, args);
    }

}
