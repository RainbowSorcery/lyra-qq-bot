package com.lyra.qqbot.utils;

import com.lyra.qqbot.common.enums.HttpAPI;
import com.lyra.qqbot.common.enums.MessageType;
import com.lyra.qqbot.config.CqHttpResouce;
import com.lyra.qqbot.entity.SendMessageResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QQBotUtils {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CqHttpResouce cqHttpResouce;

    public SendMessageResultEntity sendMessage(String messageType, Long userId, Long groupId, String message) {
        String sendMessageUrl = "";
        if (messageType.equals(MessageType.GROUP_MESSAGE.getType())) {
            sendMessageUrl = cqHttpResouce.getServer() + HttpAPI.SEND_MESSAGE +
                    "?message_type=" + messageType + "&group_id=" + groupId + "&message=" + message;
        } else if (messageType.equals(MessageType.PRIVATE_MESSAGE.getType())) {
            sendMessageUrl = cqHttpResouce.getServer() + HttpAPI.SEND_MESSAGE +
                    "?message_type=" + messageType + "&user_id=" + userId + "&message=" + message;
        }

        return restTemplate.getForObject(sendMessageUrl, SendMessageResultEntity.class);
    }
}
