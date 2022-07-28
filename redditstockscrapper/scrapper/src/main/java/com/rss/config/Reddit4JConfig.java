package com.rss.config;

import masecla.reddit4j.client.Reddit4J;
import masecla.reddit4j.client.UserAgentBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Reddit4JConfig {
    @Bean
    public Reddit4J reddit4J() {
        String USERNAME = "";
        String PASSWORD = "";
        String SECRET = "";
        String APP_ID = "";

        return Reddit4J.rateLimited().setUsername(USERNAME).setPassword(PASSWORD)
                .setClientId(APP_ID).setClientSecret(SECRET)
                .setUserAgent(
                        new UserAgentBuilder()
                                .appname("scrapper")
                                .author("Dawid Glinkowski")
                                .version("1.0")
                );
    }
}
