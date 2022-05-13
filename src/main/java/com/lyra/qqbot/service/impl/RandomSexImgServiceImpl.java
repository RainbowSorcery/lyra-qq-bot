package com.lyra.qqbot.service.impl;

import com.lyra.qqbot.entity.SendMessageResultEntity;
import com.lyra.qqbot.entity.SexImgEntity;
import com.lyra.qqbot.exception.MyGraceException;
import com.lyra.qqbot.service.IRandomSexImgService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomSexImgServiceImpl implements IRandomSexImgService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QQBotUtils qqBotUtils;

    @Override
    public void getRandomSexImg(String messageType, Long groupId, Long userId, boolean r18) {
        SexImgEntity forObject = restTemplate.getForObject("https://api.nyan.xyz/httpapi/sexphoto?r18=" + r18, SexImgEntity.class);

        if (forObject == null) {
            throw new MyGraceException("图片获取失败.", messageType, userId, groupId);
        }
        SexImgEntity.Data data = forObject.getData();
        String sexImgUrl = data.getUrl().get(0);
        String sendMessage ="[CQ:image,file=" + sexImgUrl +"]";
        SendMessageResultEntity sendMessageResultEntity = qqBotUtils.sendMessage(messageType, userId, groupId, sendMessage, false);

    }
}
