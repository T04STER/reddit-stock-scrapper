package com.rsc.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapperStockDTO {
    private String tickerSymbol;
    private Integer repeats;

 }
