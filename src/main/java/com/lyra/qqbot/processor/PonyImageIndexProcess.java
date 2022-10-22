package com.lyra.qqbot.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class PonyImageIndexProcess implements PageProcessor {
    private Integer randomNumber;

    private String messageType;
    private Long userId;
    private Long groupId;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private FullPonyImageProcessor fullPonyImageProcessor;

    @Autowired
    private HttpClientDownloader httpClientDownloader;

    private final Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);


    @Override
    public void process(Page page) {
        String xpath = "/html/body/div/main/div/div[2]/div[" + randomNumber + "]/div[2]/div/a/@href";
        String randomPonyImageUrl = "https://derpibooru.org" + page.getHtml().xpath(xpath).get();
        fullPonyImageProcessor.setSendPonyImageParam(messageType, userId, groupId);

        Spider.create(fullPonyImageProcessor)
                .setDownloader(httpClientDownloader)
                .addUrl(randomPonyImageUrl)
                .thread(threadPoolExecutor, 20)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setRandomNumber(Integer randomNumber) {
        this.randomNumber = randomNumber;
    }

    public void setSendPonyImageParam(String messageType, Long userId, Long groupId) {
        this.messageType = messageType;
        this.userId = userId;
        this.groupId = groupId;
    }
}
