package com.lyra.qqbot.Strategy.qbot.send;

import com.lyra.qqbot.service.IPonyMusic163Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendRandomMusicStrategy implements IQBotSendMessageServiceStrategy {
    @Autowired
    private IPonyMusic163Service ponyMusic163Service;

    @Override
    public void sendMessage(String messageType, Long groupId, Long userId) {
        ponyMusic163Service.sendRandomMusic(messageType, groupId, userId);
    }
}
