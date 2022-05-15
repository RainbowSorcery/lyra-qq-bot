package com.lyra.qqbot.job;

import com.lyra.qqbot.processor.PonyFeaturedImageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class PonyFeaturedImageJob {
    @Autowired
    private PonyFeaturedImageProcessor processor;

    @Scheduled(cron = "0 0 0 * * ? ")
    public void run() {
        Spider.create(processor)
                .addUrl("https://derpibooru.org/")
                .thread(5)
                .run();
    }
}
