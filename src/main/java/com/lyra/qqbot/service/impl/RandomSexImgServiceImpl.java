package com.lyra.qqbot.service.impl;

import com.lyra.qqbot.entity.SendMessageResultEntity;
import com.lyra.qqbot.entity.SexImgEntity;
import com.lyra.qqbot.exception.MyGraceException;
import com.lyra.qqbot.service.IRandomSexImgService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RandomSexImgServiceImpl implements IRandomSexImgService {
    private static final Logger log = LoggerFactory.getLogger(RandomSexImgServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Override
    public void getRandomSexImg(String messageType, Long groupId, Long userId) {
        SexImgEntity forObject = restTemplate.getForObject("https://api.lolicon.app/setu/v2?r18=2", SexImgEntity.class);

        if (forObject == null) {
            throw new MyGraceException("图片获取失败.", messageType, userId, groupId);
        }
        SexImgEntity.SexImgData data = forObject.getData().get(0);
        String sexImgUrl = data.getUrls().getOriginal();
        String sendMessage ="[CQ:image,file=" + sexImgUrl +"]";
        SendMessageResultEntity sendMessageResultEntity = qqBotUtils.sendMessage(messageType, userId, groupId, sendMessage, false);

    }

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        getRandomSexImg(messageType, groupId, userId);
    }
}
