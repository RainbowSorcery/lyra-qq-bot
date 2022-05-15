package com.lyra.qqbot.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class PonyImagePageProcessor implements PageProcessor {
    private Logger log = LoggerFactory.getLogger(PonyImagePageProcessor.class);

    private String messageType;
    private Long userId;
    private Long groupId;

    @Autowired
    private PonyImageIndexProcess ponyImageIndexProcess;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    private String url;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        int pageTotal = Integer.parseInt(page.getHtml().xpath("/html/body/div/main/div/div[3]/span/strong[2]/text()").toString().replaceAll(" ", ""));
        int pageNumber = pageTotal / 15;
        if (pageTotal % 15 != 0) {
            pageNumber += 1;
        }

        int randomPageNumber = (int) (Math.random() * pageNumber);
        url += "?page=" + randomPageNumber;

        int randImageIndex = 0;
        if (pageTotal % 15 != 0 && randomPageNumber > (pageTotal / 15)) {
            randImageIndex = (int) (Math.random() * (pageTotal % 15));
        } else {
            randImageIndex = (int) (Math.random() * 15);
        }


        ponyImageIndexProcess.setRandomNumber(randImageIndex);
        ponyImageIndexProcess.setSendPonyImageParam(messageType, userId, groupId);

        Spider.create(ponyImageIndexProcess)
                .addUrl(url)
                .thread(threadPoolExecutor, 20)
                .run();


        log.debug("pageNumber:{}", pageNumber);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSendPonyImageParam(String messageType, Long userId, Long groupId) {
        this.messageType = messageType;
        this.userId = userId;
        this.groupId = groupId;
    }
}
