package com.lyra.qqbot.Strategy;

import com.lyra.qqbot.service.ISignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Send7DayVpnSignMessage implements IQBotSendMessageServiceStrategy {
    @Autowired
    private ISignService signService;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        signService.get7TotalSignLog(messageType, groupId, userId);
    }
}
