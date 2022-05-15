package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.service.IHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendHelpStrategy implements IQBotSendMessageServiceStrategy {
    @Autowired
    private IHelpService helpService;
    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        helpService.sendHelpList(messageType, groupId, userId);
    }
}
