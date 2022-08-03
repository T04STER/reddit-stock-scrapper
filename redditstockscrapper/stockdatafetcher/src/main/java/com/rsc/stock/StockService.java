package com.rsc.stock;

import com.rsc.client.alphavantage.AlphaVantageClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
public class StockService {
    private static final long DELAY_MS = 15_000; //Due to api limit
    private final AlphaVantageClient alphaVantageClient;
    private final  StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public void saveAllStocks(List<ScrapperStockDTO> stockDTOS) {
        stockRepository.deleteAll();
        stockRepository.resetPrimaryKey();
        int counter = 0;
        int MAX_STOCKS = 10;

        for (ScrapperStockDTO stockDTO: stockDTOS) {
            if (stockDTO.getRepeats() < 3) {
                continue;
            }
            Stock stock = alphaVantageClient.getStockBySymbol(stockDTO.getTickerSymbol());
            if (stock != null) {
                stockRepository.save(stock);
                counter++;
            }

            if (counter >= MAX_STOCKS) {
                break;
            }

            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
