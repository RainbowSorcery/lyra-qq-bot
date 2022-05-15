package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.cnstant.PonyImageConstant;
import com.lyra.qqbot.common.enums.MessageType;
import com.lyra.qqbot.processor.PonyImagePageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class SendRandomBackgroundPonyImageMessage implements IQBotSendMessageServiceStrategy {
    @Autowired
    private PonyImagePageProcessor processor;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        processor.setUrl("https://derpibooru.org/tags/" + PonyImageConstant.BACKGROUND_PONY);
        processor.setSendPonyImageParam(messageType, userId, groupId);
        Spider.create(processor)
                .addUrl("https://derpibooru.org/tags/" + PonyImageConstant.BACKGROUND_PONY)
                .thread(threadPoolExecutor, 20)
                .run();

    }
}
