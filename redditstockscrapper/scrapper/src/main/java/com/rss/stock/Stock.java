package com.rss.stock;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    private String tickerSymbol;
    private Integer repeats;
}
