package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.processor.RandomTrendingPonyImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class SendTrendingPonyImageStrategy implements IQBotSendMessageServiceStrategy{
    @Autowired
    private RandomTrendingPonyImage randomTrendingPonyImage;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        randomTrendingPonyImage.setSendPonyImageParam(messageType, userId, groupId);
        int randomPage = (int) (Math.random() * 20);
        String url = "https://derpibooru.org/search?sd=desc&sf=wilson_score&q=first_seen_at.gt%3A3+days+ago" + "&page=" + randomPage;
        randomTrendingPonyImage.setUrl(url);
        Spider.create(randomTrendingPonyImage).addUrl(url).thread(threadPoolExecutor, 100).run();
    }
}
