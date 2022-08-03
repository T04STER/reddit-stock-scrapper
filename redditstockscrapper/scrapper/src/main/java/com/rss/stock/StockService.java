package com.rss.stock;

import com.rss.client.stockdatafetcher.StockDataFetcherClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class StockService {
    StockDataFetcherClient stockDataFetcherClient;
    public void sendAllStocks(HashMap<String, Integer> hashMap) {

        List<Stock> stocks = hashMap.keySet()
                .stream()
                .map(key -> new Stock(key, hashMap.get(key)))
                .sorted(((o1, o2) -> Integer.compare(o2.getRepeats(), o1.getRepeats())))
                .collect(Collectors.toList());

        stocks.forEach(stock -> log.info("{} found {} times", stock.getTickerSymbol(), stock.getRepeats()));

        stockDataFetcherClient.sendAll(stocks);
    }
}
