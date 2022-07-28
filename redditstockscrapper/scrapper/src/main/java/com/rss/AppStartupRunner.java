package com.rss;

import com.rss.task.RedditScrapper;
import com.rss.task.RedditScrapperTaskRunner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class AppStartupRunner implements ApplicationRunner {
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final RedditScrapper redditScrapper;

    private final int DELAY = 240;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread.sleep(1000);
        threadPoolTaskScheduler.scheduleWithFixedDelay(
                new RedditScrapperTaskRunner(redditScrapper),
                (DELAY * 60_000)
        );
    }
}


