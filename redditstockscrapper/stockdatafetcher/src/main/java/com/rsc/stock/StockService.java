package com.rsc.stock;

import com.rsc.client.alphavantage.AlphaVantageClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockService {
    private static final long DELAY_MS = 12_000; //Due to api limit
    private final AlphaVantageClient alphaVantageClient;
    private final  StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Async
    public List<Stock> saveAllStocks(List<ScrapperStockDTO> stockDTOS) {
        List<Stock> stocks = stockDTOS.stream().map(stockDTO -> {
            log.info("Sending request for {} symbol", stockDTO.getTickerSymbol());
            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
        e.printStackTrace();
            }
            return alphaVantageClient.getStockBySymbol(stockDTO.getTickerSymbol());
        }).filter(stock -> stock!=null).toList();

        stockRepository.deleteAll();

        stockRepository.saveAll(stocks);

        return stocks;
    }
}
