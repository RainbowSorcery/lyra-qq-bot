package com.lyra.qqbot.processor;

import com.lyra.qqbot.common.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class RandomTrendingPonyImage implements PageProcessor {
    @Autowired
    private PonyImageIndexProcess ponyImageIndexProcess;

    @Autowired
    private FullPonyImageProcessor fullPonyImageProcessor;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    private String messageType;
    private Long userId;
    private Long groupId;

    private String url;
    private final Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        int randImageIndex = (int) (Math.random() * 15);
        String indexImagePath =  "https://derpibooru.org/" + page.getHtml().xpath("/html/body/div/main/div/div[1]/div[" + randImageIndex + "]/div[2]/div/a/@href").get();

        fullPonyImageProcessor.setSendPonyImageParam(messageType, userId, groupId);
        Spider.create(fullPonyImageProcessor).addUrl(indexImagePath).thread(threadPoolExecutor, 100).run();

    }

    public void setSendPonyImageParam(String messageType, Long userId, Long groupId) {
        this.messageType = messageType;
        this.userId = userId;
        this.groupId = groupId;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
