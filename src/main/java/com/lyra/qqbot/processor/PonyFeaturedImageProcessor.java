package com.lyra.qqbot.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class PonyFeaturedImageProcessor implements PageProcessor {
    @Autowired
    private PonyFeatureImageFullProcessor ponyFeatureImageFullProcessor;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);

    @Override
    public void process(Page page) {
        String fullPageUrl = "https://derpibooru.org" + page.getHtml().xpath("/html/body/div/main/div/aside/div[1]/div/div[2]/div/a/@href");
        page.putField("PonyFeaturedImage", fullPageUrl);

        Spider.create(ponyFeatureImageFullProcessor)
                .addUrl(fullPageUrl)
                .thread(5)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
