package com.rsc.stock;

import com.rsc.client.alphavantage.AlphaVantageClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {
    StockService stockService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        return new ResponseEntity<List<Stock>>(
                stockService.getAllStocks(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<String> saveAllStocks(@RequestBody List<ScrapperStockDTO> stockDTOS) {
        stockService.saveAllStocks(stockDTOS);
        return new ResponseEntity(HttpStatus.OK);
    }


}
