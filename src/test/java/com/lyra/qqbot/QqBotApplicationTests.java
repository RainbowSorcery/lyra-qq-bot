package com.lyra.qqbot;

import com.lyra.qqbot.cnstant.PonyImageConstant;
import com.lyra.qqbot.common.enums.MessageType;
import com.lyra.qqbot.processor.PonyImagePageProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
class QqBotApplicationTests {
    @Autowired
    private PonyImagePageProcessor processor;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    void contextLoads() {
        processor.setUrl("https://derpibooru.org/tags/fanfic-colon-background%2Bpony");
        processor.setSendPonyImageParam(MessageType.PRIVATE_MESSAGE.getType(), 365373011L, 0L);
        Spider.create(processor)
                .addUrl("https://derpibooru.org/tags/" + PonyImageConstant.BACKGROUND_PONY)
                .thread(threadPoolExecutor, 20)
                .run();


    }

}
