package com.lyra.qqbot.Strategy.qbot.send;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lyra.qqbot.cnstant.PonyImageConstant;
import com.lyra.qqbot.common.response.Result;
import com.lyra.qqbot.config.PonyWebCrawiersConfig;
import com.lyra.qqbot.entity.PonyImageEntity;
import com.lyra.qqbot.processor.PonyImagePageProcessor;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SendRandomPonyImageMessage implements IQBotSendMessageServiceStrategy {

    @Autowired
    private PonyWebCrawiersConfig ponyWebCrawiersConfig;


    private String imageType;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        String url = ponyWebCrawiersConfig.getUrl()
                + ponyWebCrawiersConfig.getGroup()
                + imageType;

        ResponseEntity<Result<PonyImageEntity>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Result<PonyImageEntity>>() {
        });

        String derpibooruUrl = exchange.getBody().getData().getDerpibooruUrl();

        qqBotUtils.sendImgMessage(messageType, userId, groupId, "", derpibooruUrl);

    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageType() {
        return imageType;
    }

}
