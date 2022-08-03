package com.rsc.stock;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;


import javax.persistence.*;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @SequenceGenerator(name = "stock_id_generator", sequenceName = "stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_generator")
    private Long id;
    private String symbol;
    private Double open;
    private Double high;
    private Double low;
    private Double price;
    private Integer volume;
    private Double previousClose;
    private Double change;
    private String changePercent;

    @JsonProperty("Global Quote")
    public void unpackNested (HashMap<String, Object> global) {
        symbol = (String) global.get("01. symbol");
        open = Double.parseDouble((String) global.get("02. open"));
        high = Double.parseDouble((String) global.get("03. high"));
        low = Double.parseDouble((String) global.get("04. low"));
        price = Double.parseDouble((String) global.get("05. price"));
        volume = Integer.parseInt((String) global.get("06. volume"));
        previousClose = Double.parseDouble((String) global.get("08. previous close"));
        change = Double.parseDouble((String) global.get("09. change"));
        changePercent = (String) global.get("10. change percent");
    }
}
