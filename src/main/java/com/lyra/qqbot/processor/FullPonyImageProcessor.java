package com.lyra.qqbot.processor;

import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class FullPonyImageProcessor implements PageProcessor {
    private String messageType;
    private Long userId;
    private Long groupId;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QQBotUtils qqBotUtils;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);


    @Override
    public void process(Page page) {
        String xpath = "/html/body/div/main/div[1]/div[1]/div[4]/a[3]/@href";
        String s = page.getHtml().xpath(xpath).get();
        page.putField("fullPonyImage", page.getHtml().xpath(xpath));


        qqBotUtils.sendImgMessage(messageType, userId, groupId,  "", s);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void setSendPonyImageParam(String messageType, Long userId, Long groupId) {
        this.messageType = messageType;
        this.userId = userId;
        this.groupId = groupId;
    }
}
