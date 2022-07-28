package com.rss.task;


import com.rss.stock.StockService;
import lombok.extern.slf4j.Slf4j;
import masecla.reddit4j.client.Reddit4J;
import masecla.reddit4j.objects.RedditPost;
import masecla.reddit4j.objects.Sorting;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RedditScrapper {
    private final Reddit4J client;
    private final StockService stockService;

    public RedditScrapper(Reddit4J reddit4j, StockService stockService) {
        this.client = reddit4j;
        this.stockService = stockService;


    }

    public void scrap() throws Exception {
        HashMap<String, Integer> stockMap = scrapSubreddit("wallstreetbets");
        HashMap<String, Integer> stockMap2 = scrapSubreddit("stocks");
        stockMap2.forEach((key, value) -> {
            stockMap.merge(key, value, Integer::sum);
        });
        stockMap2 = null;

        stockService.sendAllStocks(stockMap);

    }

    private HashMap<String, Integer> scrapSubreddit(String subReddit) throws Exception {
        log.info("Connecting to reddit api");
        client.connect();
        log.info("Getting post from r/{}", subReddit);

        HashMap<String, Integer> tickers = new HashMap<>();
        Set<String> ignoreSet = Arrays.stream(new String[]{
                "CEO",
                "CHART",
                "CLICK",
                "COVID",
                "FED",
                "FOR",
                "GDP",
                "HERE",
                "LIVE",
                "LINK",
                "QUOTE",
                "SEC",
                "STOCK",
                "THIS",
                "TODAY",
                "VIX",
                "WEEK",
                "WSB"
        }).collect(Collectors.toSet());

        List<RedditPost> rp = client.getSubredditPosts(subReddit, Sorting.RISING).submit();
        log.info("Found {} posts", rp.size());
        rp.forEach(redditPost -> {
            String data = new StringBuilder(redditPost.getTitle())
                    .append(redditPost.getSelftext())
                    .toString();

            Arrays.stream(data.split("[^A-Z]+"))
                    .filter(s -> s.length() < 6 && s.length() > 2 && !ignoreSet.contains(s))
                    .forEach(s -> {
                        tickers.merge(s, 1, Integer::sum);
                    });
        });
        log.info("===============");
        return tickers;
    }
}
