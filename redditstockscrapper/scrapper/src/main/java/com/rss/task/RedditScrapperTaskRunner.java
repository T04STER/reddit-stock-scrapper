package com.rss.task;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import masecla.reddit4j.client.Reddit4J;

import java.io.IOException;
import java.util.Date;


@Slf4j
@AllArgsConstructor
public class RedditScrapperTaskRunner implements Runnable {
    RedditScrapper redditScrapper;
    @Override
    public void run() {
        log.info("Scraping started at {} on thread {}", new Date(), Thread.currentThread().getName());
        try {
            redditScrapper.scrap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
