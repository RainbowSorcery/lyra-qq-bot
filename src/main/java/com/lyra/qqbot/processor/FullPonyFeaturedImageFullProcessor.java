package com.lyra.qqbot.processor;

import com.lyra.qqbot.cnstant.RedisCacheConstant;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.TimeUnit;

@Component
public class FullPonyFeaturedImageFullProcessor implements PageProcessor {
    @Autowired
    private QQBotUtils qqBotUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000);


    @Override
    public void process(Page page) {
        String s = page.getHtml().xpath("/html/body/div/main/div[1]/div[1]/div[4]/a[3]/@href").toString();

        redisTemplate.opsForValue().set(RedisCacheConstant.PONY_FEATURED_IMG_CACHE, s, 1, TimeUnit.DAYS);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
