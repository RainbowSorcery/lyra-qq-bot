package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.cnstant.PonyImageConstant;
import com.lyra.qqbot.processor.PonyImagePageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SendRandomPonyImageMessage implements IQBotSendMessageServiceStrategy {
    @Autowired
    private PonyImagePageProcessor processor;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    private  String imageType;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        processor.setUrl("https://derpibooru.org/tags/" + imageType);
        processor.setSendPonyImageParam(messageType, userId, groupId);
        Spider.create(processor)
                .addUrl("https://derpibooru.org/tags/" + imageType)
                .thread(threadPoolExecutor, 50)
                .run();

    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
