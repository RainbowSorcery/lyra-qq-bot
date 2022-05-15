package com.lyra.qqbot;

import com.lyra.qqbot.processor.PonyFeaturedImageProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@SpringBootTest
class QqBotApplicationTests {
    @Autowired
    private PonyFeaturedImageProcessor processor;

    @Test
    void contextLoads() {
        ConsolePipeline consolePipeline = new ConsolePipeline();

        Spider.create(processor)
                .addUrl("https://derpibooru.org/")
                .thread(5)
                .run();
    }

}
