package com.lyra.qqbot.Strategy;

import com.lyra.qqbot.cnstant.RedisCacheConstant;
import com.lyra.qqbot.processor.PonyFeaturedImageProcessor;
import com.lyra.qqbot.utils.QQBotUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class RandomPonyFeaturedImgStrategy implements IQBotSendMessageServiceStrategy {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Autowired
    private PonyFeaturedImageProcessor processor;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        String featuredImgCache = redisTemplate.opsForValue().get(RedisCacheConstant.PONY_FEATURED_IMG_CACHE);

        if (StringUtils.isEmpty(featuredImgCache)) {
            Spider.create(processor)
                    .addUrl("https://derpibooru.org/")
                    .thread(5)
                    .run();
            featuredImgCache = redisTemplate.opsForValue().get(RedisCacheConstant.PONY_FEATURED_IMG_CACHE);
        }

        qqBotUtils.sendImgMessage(messageType, userId, groupId, "", featuredImgCache);
    }
}
